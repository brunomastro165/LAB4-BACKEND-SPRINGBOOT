package com.example.buensaborback.business.facade.base;

import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.entities.Base;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseFacadeImpl<E extends Base, D extends BaseDto, DC, DE, ID extends Serializable> implements BaseFacade<D, DC, DE, ID> {

    protected BaseService<E, ID> baseService;
    protected BaseMapper<E, D, DC, DE> baseMapper;

    public BaseFacadeImpl(BaseService<E, ID> baseService, BaseMapper<E, D, DC, DE> baseMapper) {
        this.baseService = baseService;
        this.baseMapper = baseMapper;
    }

    public D createNew(DC request) {
        System.out.println("EstoyEnFacade");
        // Convierte a entidad
        var entityToCreate = baseMapper.toEntityCreate(request);
        // Graba una entidad
        var entityCreated = baseService.create(entityToCreate);
        // convierte a Dto para devolver
        return baseMapper.toDTO(entityCreated);
    }

    public D getById(ID id) {
        // Busca una entidad por id
        var entity = baseService.getById(id);
        // convierte la entidad a DTO
        return baseMapper.toDTO(entity);
    }

    public List<D> getAll() {
        // trae una lista de entidades
        var entities = baseService.getAll();
        //  devuelve una lista de DTO
        return entities
                .stream()
                .map(baseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(ID id) {
        var entity = baseService.getById(id);
        baseService.deleteById(id);
    }

    public D update(DE request, ID id) {
        var entityToUpdate = baseService.getById(id);
        var entityUpdatedByMapper = baseMapper.toUpdate(entityToUpdate, request);
        var entityUpdatedByService = baseService.update(entityUpdatedByMapper, id);
        return baseMapper.toDTO(entityUpdatedByService);
    }

}
