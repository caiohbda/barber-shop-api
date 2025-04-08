package br.com.caio.barbershopapi.service;

import br.com.caio.barbershopapi.entity.ClientEntity;

public interface IClientService {

    ClientEntity save(final ClientEntity entity);

    ClientEntity update(final ClientEntity entity);

    void delete(final long id);
}
