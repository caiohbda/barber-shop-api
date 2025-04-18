package br.com.caio.barbershopapi.entity;


import jakarta.persistence.*;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(
        name="SCHEDULES",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_SCHEDULE_INTERVAL", columnNames = {"start_at", "end_at"}),
        }
)
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, name="start_in")
    private OffsetDateTime startAt;

    @Column(nullable = false, name="end_in")
    private OffsetDateTime endAt;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client = new ClientEntity();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleEntity that = (ScheduleEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(startAt, that.startAt) && Objects.equals(endAt, that.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startAt, endAt);
    }

    public OffsetDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(OffsetDateTime startAt) {
        this.startAt = startAt;
    }

    public OffsetDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(OffsetDateTime endAt) {
        this.endAt = endAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}
