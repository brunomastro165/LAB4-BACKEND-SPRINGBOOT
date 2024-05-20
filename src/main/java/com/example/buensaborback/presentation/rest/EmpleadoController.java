package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.EmpleadoFacadeImpl;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoCreateDto;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoDto;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.presentation.base.BaseControllerImpl;

public class EmpleadoController extends BaseControllerImpl<Empleado, EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto, Long, EmpleadoFacadeImpl> {
    public EmpleadoController(EmpleadoFacadeImpl facade) {
        super(facade);
    }
}
