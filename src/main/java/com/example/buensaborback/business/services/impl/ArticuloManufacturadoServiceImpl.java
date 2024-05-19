package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.ArticuloManufacturadoService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.ArticuloManufacturadoDetalle;
import com.example.buensaborback.repositories.ArticuloManufacturadoDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloManufacturadoServiceImpl extends BaseServiceImpl<ArticuloManufacturado, Long> implements ArticuloManufacturadoService {
    @Autowired
    ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;

    @Override
    public ArticuloManufacturado create(ArticuloManufacturado articuloManufacturado) {
        var detalles = articuloManufacturado.getArticuloManufacturadoDetalles();


        var newEntity = baseRepository.save(articuloManufacturado);
        for (ArticuloManufacturadoDetalle detalle : detalles) {
            try {
                System.out.println(detalle);
                articuloManufacturadoDetalleRepository.save(detalle);
            } catch (Exception e) {

            }

        }
        return newEntity;
    }
}
