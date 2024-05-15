package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.LocalidadFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.LocalidadService;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Localidad.LocalidadCreateDto;
import com.example.buensaborback.domain.dto.Localidad.LocalidadDto;
import com.example.buensaborback.domain.entities.Localidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalidadFacadeImpl extends BaseFacadeImpl<Localidad, LocalidadDto, LocalidadCreateDto, LocalidadCreateDto, Long> implements LocalidadFacade {
    @Autowired
    LocalidadService localidadService;

    public LocalidadFacadeImpl(BaseService<Localidad, Long> baseService, BaseMapper<Localidad, LocalidadDto, LocalidadCreateDto, LocalidadCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Override
    public List<LocalidadDto> findByProvinciaId(Long id) {
        // trae una lista de entidades
        var entities = localidadService.findByProvinciaId(id);
        //  devuelve una lista de DTO
        return entities
                .stream()
                .map(baseMapper::toDTO)
                .collect(Collectors.toList());
    }
}
