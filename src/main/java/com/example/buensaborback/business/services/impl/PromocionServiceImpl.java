package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.PromocionService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.repositories.PromocionDetalleRepository;
import com.example.buensaborback.repositories.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromocionServiceImpl extends BaseServiceImpl<Promocion, Long> implements PromocionService {
    @Autowired
    PromocionRepository promocionRepository;
    public Promocion create(Promocion request) {
        System.out.println("Estoy en service");
        Promocion newEntity = baseRepository.save(request);
        newEntity.getSucursales().forEach(sucursal -> promocionRepository.
                insertIntoSucursalPromocion(sucursal.getId(), newEntity.getId()));
        return newEntity;
    }

}