package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.LocalidadFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.LocalidadDTO;
import com.example.buensaborback.domain.entities.Localidad;
import org.springframework.stereotype.Service;

@Service
public class LocalidadFacadeImpl extends BaseFacadeImpl<Localidad, LocalidadDTO, Long> implements LocalidadFacade {
    public LocalidadFacadeImpl(BaseService<Localidad, Long> baseService, BaseMapper<Localidad, LocalidadDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}
