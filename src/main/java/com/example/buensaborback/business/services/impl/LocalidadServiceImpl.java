package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.LocalidadService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.Localidad;
import com.example.buensaborback.repositories.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad, Long> implements LocalidadService {

    @Autowired
    LocalidadRepository localidadRepository;

    @Override
    public List<Localidad> findByProvinciaId(Long id) {
        return localidadRepository.findByProvinciaId(id);
    }
}
