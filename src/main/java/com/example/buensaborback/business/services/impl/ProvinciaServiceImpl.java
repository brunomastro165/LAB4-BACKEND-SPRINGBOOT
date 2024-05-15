package com.example.buensaborback.business.services.impl;


import com.example.buensaborback.business.services.ProvinciaService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.Provincia;
import com.example.buensaborback.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaServiceImpl extends BaseServiceImpl<Provincia, Long> implements ProvinciaService {
    @Autowired
    ProvinciaRepository provinciaRepository;

    @Override
    public List<Provincia> findByPaisId(Long id) {
        return provinciaRepository.findByPaisId(id);
    }
}
