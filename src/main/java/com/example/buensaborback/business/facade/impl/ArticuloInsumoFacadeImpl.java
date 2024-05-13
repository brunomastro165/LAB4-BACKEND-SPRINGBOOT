package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ArticuloInsumoFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.ArticuloInsumoDTO;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.springframework.stereotype.Service;

@Service
public class ArticuloInsumoFacadeImpl extends BaseFacadeImpl<ArticuloInsumo, ArticuloInsumoDTO, Long> implements ArticuloInsumoFacade {
    public ArticuloInsumoFacadeImpl(BaseService<ArticuloInsumo, Long> baseService, BaseMapper<ArticuloInsumo, ArticuloInsumoDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}

