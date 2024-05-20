package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.Factura.FacturaCreateDto;
import com.example.buensaborback.domain.dto.Factura.FacturaDto;

public interface FacturaFacade extends BaseFacade<FacturaDto, FacturaCreateDto, FacturaCreateDto, Long> {
}
