package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.PaisService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.Pais;
import com.example.buensaborback.repositories.BaseRepository;
import com.example.buensaborback.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisServiceImpl extends BaseServiceImpl<Pais, Long> implements PaisService {
    @Autowired
    private PaisRepository paisRepository;

    public PaisServiceImpl(BaseRepository<Pais, Long> baseRepository, PaisRepository paisRepository) {
        super(baseRepository);
        this.paisRepository = paisRepository;
    }
}
