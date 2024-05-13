package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.PromocionDetalleFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.PromocionDetalleDTO;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import org.springframework.stereotype.Service;

@Service
public class PromocionDetalleFacadeImpl extends BaseFacadeImpl<PromocionDetalle, PromocionDetalleDTO, Long> implements PromocionDetalleFacade {
    public PromocionDetalleFacadeImpl(BaseService<PromocionDetalle, Long> baseService, BaseMapper<PromocionDetalle, PromocionDetalleDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}
