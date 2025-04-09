package br.com.caio.barbershopapi.controller;

import br.com.caio.barbershopapi.controller.request.SaveClientRequest;
import br.com.caio.barbershopapi.controller.request.UpdateClientRequest;
import br.com.caio.barbershopapi.controller.response.ClientDetailResponse;
import br.com.caio.barbershopapi.controller.response.ListClientResponse;
import br.com.caio.barbershopapi.controller.response.SaveClientResponse;
import br.com.caio.barbershopapi.controller.response.UpdateClientResponse;
import br.com.caio.barbershopapi.mapper.IClientMapper;
import br.com.caio.barbershopapi.service.IClientService;
import br.com.caio.barbershopapi.service.query.IClientQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {

    private IClientService service;
    private IClientQueryService queryService;
    private IClientMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request){
        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @PutMapping("{id}")
    UpdateClientResponse update(@PathVariable final long id, @RequestBody @Valid final UpdateClientRequest request){
        var entity = mapper.toEntity(id, request);
        service.update(entity);
        return mapper.toUpdateResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final long id){
        service.delete(id);
    }

    @GetMapping("{id}")
    ClientDetailResponse findById(@PathVariable final long id){
        var entity = queryService.findById(id);
        return mapper.toDetailResponse(entity);
    }

    @GetMapping
    List<ListClientResponse> list(){
        var entities = queryService.list();
        return mapper.toListResponse(entities);
    }

}