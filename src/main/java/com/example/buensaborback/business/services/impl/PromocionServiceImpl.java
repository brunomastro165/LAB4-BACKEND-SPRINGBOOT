package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.PromocionService;
import com.example.buensaborback.business.services.SucursalService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleCreateDto;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.repositories.ArticuloRepository;
import com.example.buensaborback.repositories.PromocionDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PromocionServiceImpl extends BaseServiceImpl<Promocion, Long> implements PromocionService {
    @Autowired
    PromocionDetalleRepository promocionDetalleRepository;
    //Esto ya anda con el cascade ALL
    /*
    @Override
    public Promocion create(Promocion promocion) {
        System.out.println("hola puto");
        var detalles = promocion.getDetalles();
        Set<PromocionDetalle> newDetalles = new HashSet<>();
        System.out.println(detalles);



        System.out.println("concha");
        for (PromocionDetalle detalle : detalles) {
            try {
                System.out.println(detalle);
                newDetalles.add(promocionDetalleRepository.save(detalle));
            } catch (Exception e) {

            }

        }
        promocion.setDetalles(newDetalles);
        var newEntity = baseRepository.save(promocion);
        return newEntity;
    }

     */

}