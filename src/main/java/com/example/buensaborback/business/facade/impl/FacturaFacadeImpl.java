package com.example.buensaborback.business.facade.impl;


import com.example.buensaborback.business.facade.FacturaFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;

import com.example.buensaborback.domain.dto.Factura.FacturaCreateDto;
import com.example.buensaborback.domain.dto.Factura.FacturaDto;

import com.example.buensaborback.domain.entities.Factura;

public class FacturaFacadeImpl extends BaseFacadeImpl<Factura, FacturaDto, FacturaCreateDto, FacturaCreateDto, Long> implements FacturaFacade {
    public FacturaFacadeImpl(BaseService<Factura, Long> baseService, BaseMapper<Factura, FacturaDto, FacturaCreateDto, FacturaCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
