package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.ArticuloManufacturadoService;
import com.example.buensaborback.business.services.PromocionService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.ArticuloManufacturadoDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloManufacturadoServiceImpl extends BaseServiceImpl<ArticuloManufacturado, Long> implements ArticuloManufacturadoService {

    @Autowired
    private PromocionService promocionService;

    @Override
    public void deleteById(Long id) {
        var entity = getById(id);
        boolean isInPromocion = promocionService.getAll().stream()
                .filter(promocion -> !promocion.isEliminado())
                .flatMap(promocion -> promocion.getDetalles().stream())
                .anyMatch(detalle -> detalle.getArticulo().getId() == id);
        //Borra si no esta en un promo que este activa
        if (!isInPromocion) {
            baseRepository.delete(entity);
        }
    }

    @Override
    public void activateById(Long id) {
        var entity = baseRepository.getEliminadoById(id);
        boolean allInsumosActive = entity.getArticuloManufacturadoDetalles().stream()
                .map(ArticuloManufacturadoDetalle::getArticuloInsumo)
                .allMatch(articuloInsumo -> !articuloInsumo.isEliminado());

        // Activa el artículo solo si todos sus insumos están activos
        if (allInsumosActive) {
            entity.setEliminado(false);
            baseRepository.save(entity);
        }
    }


}
