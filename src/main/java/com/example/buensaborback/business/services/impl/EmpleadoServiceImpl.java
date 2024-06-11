package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.EmpleadoService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl extends BaseServiceImpl<Empleado, Long> implements EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    public List<Empleado> getPorSucursal(Long id) {
        List<Empleado> empleados = empleadoRepository.findEmpleadosBySucursalId(id);
        return empleados.stream()
                .filter(empleado -> !empleado.isEliminado())
                .collect(Collectors.toList());
    }


}