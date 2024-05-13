package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.FacturaFacadeImpl;
import com.example.buensaborback.domain.dtos.FacturaDTO;
import com.example.buensaborback.domain.entities.Factura;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/factura")
public class FacturaController extends BaseControllerImpl<Factura, FacturaDTO, Long, FacturaFacadeImpl> {
    public FacturaController(FacturaFacadeImpl facade) {
        super(facade);
    }
}
