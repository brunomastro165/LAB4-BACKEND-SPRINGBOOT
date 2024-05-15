package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ArticuloManufacturadoDetalleFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturadoDetalle;
import org.springframework.stereotype.Service;

@Service
public class ArticuloManufacturadoDetalleFacadeImpl extends BaseFacadeImpl<ArticuloManufacturadoDetalle, ArticuloManufacturadoDetalleDto, ArticuloManufacturadoDetalleCreateDto, ArticuloManufacturadoDetalleCreateDto, Long> implements ArticuloManufacturadoDetalleFacade {
    public ArticuloManufacturadoDetalleFacadeImpl(BaseService<ArticuloManufacturadoDetalle, Long> baseService, BaseMapper<ArticuloManufacturadoDetalle, ArticuloManufacturadoDetalleDto, ArticuloManufacturadoDetalleCreateDto, ArticuloManufacturadoDetalleCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
