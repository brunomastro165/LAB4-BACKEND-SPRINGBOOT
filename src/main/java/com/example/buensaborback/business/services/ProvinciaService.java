package com.example.buensaborback.business.services;

import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.entities.Provincia;

import java.util.List;

public interface ProvinciaService extends BaseService<Provincia, Long> {
    List<Provincia> findByPaisId(Long id);
}
