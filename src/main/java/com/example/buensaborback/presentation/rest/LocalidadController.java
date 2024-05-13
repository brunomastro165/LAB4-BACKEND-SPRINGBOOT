package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.LocalidadFacadeImpl;
import com.example.buensaborback.domain.dtos.LocalidadDTO;
import com.example.buensaborback.domain.entities.Localidad;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/localidad")
public class LocalidadController extends BaseControllerImpl<Localidad, LocalidadDTO, Long, LocalidadFacadeImpl> {
    public LocalidadController(LocalidadFacadeImpl facade) {
        super(facade);
    }
}
