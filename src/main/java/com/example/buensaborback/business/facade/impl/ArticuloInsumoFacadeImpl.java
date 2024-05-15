package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ArticuloInsumoFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.Insumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.springframework.stereotype.Service;

@Service
public class ArticuloInsumoFacadeImpl extends BaseFacadeImpl<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoCreateDto, Long> implements ArticuloInsumoFacade {
    public ArticuloInsumoFacadeImpl(BaseService<ArticuloInsumo, Long> baseService, BaseMapper<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
