package br.com.caio.barbershopapi.repository;

import br.com.caio.barbershopapi.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClientRepository extends JpaRepository<ClientEntity, Long> {

    boolean existsByEmail(final String email);

    Optional<ClientEntity> findByEmail(final String email);

    boolean existsByPhone(final String phone);

    Optional<ClientEntity> findByPhone(final String phone);
}
