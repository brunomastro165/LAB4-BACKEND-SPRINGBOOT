package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.PromocionDetalleService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.repositories.BaseRepository;
import com.example.buensaborback.repositories.PromocionDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromocionDetalleServiceImpl extends BaseServiceImpl<PromocionDetalle, Long> implements PromocionDetalleService {
    @Autowired
    private PromocionDetalleRepository promocionDetalleRepository;

    public PromocionDetalleServiceImpl(BaseRepository<PromocionDetalle, Long> baseRepository, PromocionDetalleRepository promocionDetalleRepository) {
        super(baseRepository);
        this.promocionDetalleRepository = promocionDetalleRepository;
    }
}
