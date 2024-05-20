package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.FacturaFacadeImpl;
import com.example.buensaborback.domain.dto.Factura.FacturaCreateDto;
import com.example.buensaborback.domain.dto.Factura.FacturaDto;
import com.example.buensaborback.domain.entities.Factura;
import com.example.buensaborback.presentation.base.BaseControllerImpl;

public class FacturaController extends BaseControllerImpl<Factura, FacturaDto, FacturaCreateDto, FacturaCreateDto, Long, FacturaFacadeImpl> {
    public FacturaController(FacturaFacadeImpl facade) {
        super(facade);
    }
}
