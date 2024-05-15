package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.EmpresaService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmpresaServiceImpl extends BaseServiceImpl<Empresa, Long> implements EmpresaService {

    @Override
    public Empresa getEmpresaSucursales(Long idEmpresa) {
        Empresa empresa = baseRepository.getById(idEmpresa);
        Set<Sucursal> sucursales = empresa.getSucursales().stream()
                .filter(sucursal -> !sucursal.isEliminado())
                .collect(Collectors.toSet());
        empresa.setSucursales(sucursales);
        return empresa;
    }
}
