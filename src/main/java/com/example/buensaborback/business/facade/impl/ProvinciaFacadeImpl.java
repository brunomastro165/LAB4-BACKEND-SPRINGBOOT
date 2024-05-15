package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ProvinciaFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.ProvinciaService;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Provincia.ProvinciaCreateDto;
import com.example.buensaborback.domain.dto.Provincia.ProvinciaDto;
import com.example.buensaborback.domain.entities.Provincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaFacadeImpl extends BaseFacadeImpl<Provincia, ProvinciaDto, ProvinciaCreateDto, ProvinciaCreateDto, Long> implements ProvinciaFacade {

    @Autowired
    ProvinciaService provinciaService;

    public ProvinciaFacadeImpl(BaseService<Provincia, Long> baseService, BaseMapper<Provincia, ProvinciaDto, ProvinciaCreateDto, ProvinciaCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Override
    public List<ProvinciaDto> findByPaisId(Long id) {
        return baseMapper.toDTOsList(provinciaService.findByPaisId(id));
    }
}
