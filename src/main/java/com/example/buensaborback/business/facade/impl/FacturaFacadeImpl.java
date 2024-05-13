package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.FacturaFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.FacturaDTO;
import com.example.buensaborback.domain.entities.Factura;
import org.springframework.stereotype.Service;

@Service
public class FacturaFacadeImpl extends BaseFacadeImpl<Factura, FacturaDTO, Long> implements FacturaFacade {
    public FacturaFacadeImpl(BaseService<Factura, Long> baseService, BaseMapper<Factura, FacturaDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}
