package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.facade.ArticuloManufacturadoFacade;
import com.example.buensaborback.business.services.ArticuloInsumoService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.repositories.ArticuloRepository;
import com.example.buensaborback.repositories.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticuloInsumoServiceImpl extends BaseServiceImpl<ArticuloInsumo, Long> implements ArticuloInsumoService {

    @Autowired
    private PromocionRepository promocionRepository;
    @Autowired
    private ArticuloManufacturadoFacade articuloManufacturadoFacade;
    @Autowired
    private ArticuloRepository articuloRepository;

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
        } else {
            throw new RuntimeException("No se puede borrar la entidad porque está en una promoción activa o en un ArticuloManufacturado activo");

        }

    }

    public List<Articulo> getAllArticulos(String searchString, Long idSucursal, Integer limit, Long startId) {
        List<Articulo> articulos = articuloRepository.getAll();
        List<Articulo> filteredArticulos;
        if (searchString == null || searchString == "") {
            filteredArticulos = articulos.stream()
                    .filter(a ->
                            !a.isEliminado()
                                    && a.getCategoria() != null
                                    && a.getCategoria().getSucursales().stream().anyMatch(s -> s.getId().equals(idSucursal)))
                    .collect(Collectors.toList());
        } else
            filteredArticulos = articulos.stream()
                    .filter(a -> a.getDenominacion().toLowerCase().contains(searchString.toLowerCase())
                            && !a.isEliminado()
                            && a.getCategoria() != null
                            && a.getCategoria().getSucursales().stream().anyMatch(s -> s.getId().equals(idSucursal)))
                    .collect(Collectors.toList());
        List<Articulo> articulosResponse = new ArrayList<>();
        // Establece la categoría e imágenes en null después de filtrar
        for (Articulo articulo : filteredArticulos) {
            ArticuloInsumo articuloInsumo = new ArticuloInsumo();
            articuloInsumo.setUnidadMedida(articulo.getUnidadMedida());
            articuloInsumo.setPrecioCompra(articulo.getPrecioVenta());
            articuloInsumo.setDenominacion(articulo.getDenominacion());
            articuloInsumo.setId(articulo.getId());
            articuloInsumo.setEliminado(articulo.isEliminado());
            articulosResponse.add(articuloInsumo);
        }
        if (startId != null) {
            int startIndex = (startId.intValue() - 1) * limit;
            int endIndex = Math.min(startIndex + limit, articulosResponse.size());
            if (startIndex < articulosResponse.size()) {
                articulosResponse = articulosResponse.subList(startIndex, endIndex);
            } else {
                articulosResponse = new ArrayList<>();
            }
        }
        return articulosResponse;
    }

    public List<ArticuloInsumo> filtrarArticulos(String searchString, Long idSucursal, Integer limit, Long startId) {
        List<ArticuloInsumo> allArticulos = getAll();
        List<ArticuloInsumo> filteredArticulos;
        if (searchString == null || searchString == "") {
            filteredArticulos = allArticulos.stream()
                    .filter(a ->
                            !a.isEliminado()
                                    && a.getCategoria().getSucursales().stream().anyMatch(s -> s.getId().equals(idSucursal)))
                    .collect(Collectors.toList());
        } else {
            filteredArticulos = allArticulos.stream()
                    .filter(a -> a.getDenominacion().toLowerCase().contains(searchString.toLowerCase())
                            && !a.isEliminado()
                            && a.getCategoria().getSucursales().stream().anyMatch(s -> s.getId().equals(idSucursal)))
                    .collect(Collectors.toList());
        }

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
