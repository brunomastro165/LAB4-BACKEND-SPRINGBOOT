package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.ArticuloManufacturadoService;
import com.example.buensaborback.business.services.PromocionService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.ArticuloManufacturadoDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                .anyMatch(detalle -> detalle.getArticulo() != null
                        && detalle.getArticulo().getId() == id);
        //Borra si no esta en un promo que este activa
        if (!isInPromocion) {
            baseRepository.delete(entity);
        } else
            throw new RuntimeException("No se puede borrar la entidad porque está en una promoción activa o en un ArticuloManufacturado activo");
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

    public List<ArticuloManufacturado> getPorString(String searchString, Long idSucursal, Integer limit, Long startId) {
        List<ArticuloManufacturado> articulos = getAll();
        List<ArticuloManufacturado> filteredArticulos;
        if (searchString == null || searchString == "")
            filteredArticulos = articulos.stream()
                    .filter(a ->
                            !a.isEliminado()
                                    && a.getCategoria() != null
                                    && a.getCategoria().getSucursales().stream().anyMatch(s -> s.getId().equals(idSucursal)))
                    .collect(Collectors.toList());
        else
            filteredArticulos = articulos.stream()
                    .filter(a -> (searchString == null || a.getDenominacion().toLowerCase().contains(searchString.toLowerCase()))
                            && !a.isEliminado()
                            && a.getCategoria() != null
                            && a.getCategoria().getSucursales().stream().anyMatch(s -> s.getId().equals(idSucursal)))
                    .collect(Collectors.toList());

        if (startId != null) {
            int startIndex = (startId.intValue() - 1) * limit;
            int endIndex = Math.min(startIndex + limit, filteredArticulos.size());
            if (startIndex < filteredArticulos.size()) {
                filteredArticulos = filteredArticulos.subList(startIndex, endIndex);
            } else {
                filteredArticulos = new ArrayList<>();
            }
        }
        return filteredArticulos;
    }


}
