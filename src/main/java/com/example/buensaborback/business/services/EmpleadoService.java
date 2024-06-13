package com.example.buensaborback.business.services;

import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoDto;
import com.example.buensaborback.domain.entities.Empleado;

import java.util.List;

public interface EmpleadoService extends BaseService<Empleado, Long> {
    List<Empleado> getPorSucursal(Long id, String searchString);
    List<Empleado> getDeliverys();
}
