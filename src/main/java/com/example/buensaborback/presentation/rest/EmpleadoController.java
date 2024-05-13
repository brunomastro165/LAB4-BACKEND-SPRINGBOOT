package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.EmpleadoFacadeImpl;
import com.example.buensaborback.domain.dtos.EmpleadoDTO;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/empleado")
public class EmpleadoController extends BaseControllerImpl<Empleado, EmpleadoDTO, Long, EmpleadoFacadeImpl> {
    public EmpleadoController(EmpleadoFacadeImpl facade) {
        super(facade);
    }
}
