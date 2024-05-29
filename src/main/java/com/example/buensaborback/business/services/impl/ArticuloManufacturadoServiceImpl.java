package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.ArticuloManufacturadoService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.repositories.ArticuloManufacturadoDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloManufacturadoServiceImpl extends BaseServiceImpl<ArticuloManufacturado, Long> implements ArticuloManufacturadoService {
    @Autowired
    ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;
    //Esto ya lo hace el cascade TYPE.ALL creo
/*
    @Override
    public ArticuloManufacturado create(ArticuloManufacturado articuloManufacturado) {
        var detalles = articuloManufacturado.getArticuloManufacturadoDetalles();
        Set<ArticuloManufacturadoDetalle> newDetalles = new HashSet<>();



        for (ArticuloManufacturadoDetalle detalle : detalles) {
            try {
                System.out.println(detalle);
                newDetalles.add(articuloManufacturadoDetalleRepository.save(detalle));
            } catch (Exception e) {

            }

        }
        articuloManufacturado.setArticuloManufacturadoDetalles(newDetalles);
        var newEntity = baseRepository.save(articuloManufacturado);
        return newEntity;
    }

 */
}
