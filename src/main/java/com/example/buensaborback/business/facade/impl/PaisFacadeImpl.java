package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.PaisFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.PaisDTO;
import com.example.buensaborback.domain.entities.Pais;
import org.springframework.stereotype.Service;

@Service
public class PaisFacadeImpl extends BaseFacadeImpl<Pais, PaisDTO, Long> implements PaisFacade {
    public PaisFacadeImpl(BaseService<Pais, Long> baseService, BaseMapper<Pais, PaisDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}
