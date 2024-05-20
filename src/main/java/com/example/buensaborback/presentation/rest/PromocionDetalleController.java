package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.PromocionDetalleFacadeImpl;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleCreateDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.presentation.base.BaseControllerImpl;

public class PromocionDetalleController extends BaseControllerImpl<PromocionDetalle, PromocionDetalleDto, PromocionDetalleCreateDto, PromocionDetalleCreateDto, Long, PromocionDetalleFacadeImpl> {
    public PromocionDetalleController(PromocionDetalleFacadeImpl facade) {
        super(facade);
    }
}
