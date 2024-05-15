package com.example.buensaborback.business.services;

import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.entities.Localidad;

import java.util.List;

public interface LocalidadService extends BaseService<Localidad, Long> {
    List<Localidad> findByProvinciaId(Long id);
}
