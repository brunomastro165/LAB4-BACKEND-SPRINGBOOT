package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.DomicilioFacadeImpl;
import com.example.buensaborback.domain.dtos.DomicilioDTO;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/domicilio")
public class DomicilioController extends BaseControllerImpl<Domicilio, DomicilioDTO, Long, DomicilioFacadeImpl> {
    public DomicilioController(DomicilioFacadeImpl facade) {
        super(facade);
    }
}
