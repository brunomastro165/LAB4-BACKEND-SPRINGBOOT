package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoCreateDto;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoDto;

public interface EmpleadoFacade extends BaseFacade<EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto, Long> {
}
