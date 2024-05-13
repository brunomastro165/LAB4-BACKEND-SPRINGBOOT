package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.PromocionService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.repositories.BaseRepository;
import com.example.buensaborback.repositories.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromocionServiceImpl extends BaseServiceImpl<Promocion, Long> implements PromocionService {
    @Autowired
    private PromocionRepository promocionRepository;

    public PromocionServiceImpl(BaseRepository<Promocion, Long> baseRepository, PromocionRepository promocionRepository) {
        super(baseRepository);
        this.promocionRepository = promocionRepository;
    }
}
