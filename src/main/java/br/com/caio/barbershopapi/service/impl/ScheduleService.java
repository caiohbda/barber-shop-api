package br.com.caio.barbershopapi.service.impl;

import br.com.caio.barbershopapi.entity.ScheduleEntity;
import br.com.caio.barbershopapi.repository.IScheduleRepository;
import br.com.caio.barbershopapi.service.IScheduleService;
import br.com.caio.barbershopapi.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService {

    private IScheduleRepository repository;
    private IScheduleQueryService queryService;

    @Override
    public ScheduleEntity save(final ScheduleEntity entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());

        return repository.save(entity);
    }

    @Override
    public void delete(final long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}