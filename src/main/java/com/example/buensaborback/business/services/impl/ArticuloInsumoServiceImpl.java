package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.facade.ArticuloManufacturadoFacade;
import com.example.buensaborback.business.services.ArticuloInsumoService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.repositories.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloInsumoServiceImpl extends BaseServiceImpl<ArticuloInsumo, Long> implements ArticuloInsumoService {

    @Autowired
    private PromocionRepository promocionRepository;
    @Autowired
    private ArticuloManufacturadoFacade articuloManufacturadoFacade;

    @Override
    public void deleteById(Long id) {
        var entity = getById(id);

        boolean isInPromocion = promocionRepository.getAll().stream()
                .filter(promocion -> !promocion.isEliminado())
                .flatMap(promocion -> promocion.getDetalles().stream())
                .anyMatch(detalle -> detalle.getArticulo() != null && detalle.getArticulo().getId() == id);
        boolean isInManufacturadoActivo = articuloManufacturadoFacade.getAll().stream()
                .anyMatch(manufacturado ->
                        !manufacturado.isEliminado() &&
                                manufacturado.getArticuloManufacturadoDetalles().stream()
                                        .anyMatch(detalle -> detalle.getArticuloInsumo() != null
                                                && detalle.getArticuloInsumo().getId().equals(entity.getId()))
                );

        // Borra si no está en una promo que esté activa y si no está en un ArticuloManufacturado que esté activo
        if (!isInPromocion && !isInManufacturadoActivo) {
            baseRepository.delete(entity);
        }else{
            throw new RuntimeException("No se puede borrar la entidad porque está en una promoción activa o en un ArticuloManufacturado activo");

        }

    }

}
