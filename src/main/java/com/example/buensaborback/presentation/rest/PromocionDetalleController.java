package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.PromocionDetalleFacadeImpl;
import com.example.buensaborback.domain.dtos.PromocionDetalleDTO;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/promocionDetalle")
public class PromocionDetalleController extends BaseControllerImpl<PromocionDetalle, PromocionDetalleDTO, Long, PromocionDetalleFacadeImpl> {
    public PromocionDetalleController(PromocionDetalleFacadeImpl facade) {
        super(facade);
    }
}
