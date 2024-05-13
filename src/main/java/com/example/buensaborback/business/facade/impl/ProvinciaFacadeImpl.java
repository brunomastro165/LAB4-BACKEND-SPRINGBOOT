package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ProvinciaFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.ProvinciaDTO;
import com.example.buensaborback.domain.entities.Provincia;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaFacadeImpl extends BaseFacadeImpl<Provincia, ProvinciaDTO, Long> implements ProvinciaFacade {
    public ProvinciaFacadeImpl(BaseService<Provincia, Long> baseService, BaseMapper<Provincia, ProvinciaDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}