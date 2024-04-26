package com.example.buensaborback.services.impl;

import com.example.buensaborback.services.IDomicilioService;
import com.example.buensaborback.services.ILocalidadService;
import com.example.buensaborback.entities.Domicilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomicilioServiceImpl extends BaseServiceImpl<Domicilio,Long> implements IDomicilioService {

    @Autowired
    private ILocalidadService localidadService;

    @Override
    public Domicilio asignarLocalidad(Long id, Long idLocalidad) {
        var domicilio = getById(id);
        var localidad = localidadService.getById(idLocalidad);
        domicilio.setLocalidad(localidad);
        return update(domicilio);
    }
}
