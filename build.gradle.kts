import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "br.com.dio"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

val mapStructVersion = "1.6.3"

repositories {
	mavenCentral()
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

val flywayVersion = "10.14.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.flywaydb:flyway-core:$flywayVersion")
	implementation("org.flywaydb:flyway-database-postgresql:$flywayVersion")
	implementation("org.mapstruct:mapstruct:$mapStructVersion")

	compileOnly("org.projectlombok:lombok")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("org.postgresql:postgresql")

	annotationProcessor("org.mapstruct:mapstruct-processor:$mapStructVersion")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jar {
	manifest {
		attributes["Main-Class"] = "br.com.caio.barbershopapi.BarberShopApiApplication"
	}
}

tasks.named("build") {
	doLast {
		val trigger = file("src/main/resources/trigger.txt")
		if (!trigger.exists()) {
			trigger.createNewFile()
		}
		trigger.writeText(Date().time.toString())
	}
}

tasks.register("generateFlywayMigrationFile") {
	description = "Generate flyway migration"
	group = "Flyway"

	doLast {
		val migrationsDir = file("src/main/resources/db/migration")
		if (!migrationsDir.exists()) {
			migrationsDir.mkdirs()
		}

		val migrationNameFromConsole = project.findProperty("migrationName") as String?
			?: throw IllegalArgumentException("Você deve fornecer um nome para a migração usando o parâmetro -PmigrationName=<nome>.")

		val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
		val migrationName = "V${timestamp}__${migrationNameFromConsole}.sql"

		val migrationFile = file("${migrationsDir.path}/$migrationName")
		migrationFile.writeText("-- $migrationName generated in ${migrationsDir.path}")

		logger.lifecycle("Migration file created: ${migrationFile.path}")
	}
}
