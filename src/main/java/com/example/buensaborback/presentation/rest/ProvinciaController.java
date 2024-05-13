package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ProvinciaFacadeImpl;
import com.example.buensaborback.domain.dtos.ProvinciaDTO;
import com.example.buensaborback.domain.entities.Provincia;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/provincia")
public class ProvinciaController extends BaseControllerImpl<Provincia, ProvinciaDTO, Long, ProvinciaFacadeImpl> {
    public ProvinciaController(ProvinciaFacadeImpl facade) {
        super(facade);
    }
}
