package br.com.caio.barbershopapi.service;

import br.com.caio.barbershopapi.entity.ScheduleEntity;

public interface IScheduleService {
    ScheduleEntity save(final ScheduleEntity entity);

    void delete(final long id);

}
