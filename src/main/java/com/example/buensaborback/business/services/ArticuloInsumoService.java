package com.example.buensaborback.business.services;

import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.ArticuloInsumo;

import java.util.List;

public interface ArticuloInsumoService extends BaseService<ArticuloInsumo, Long> {
    List<Articulo> getAllArticulos(String searchString, Long idSucursal, Integer limit, Long startId);

    List<ArticuloInsumo> filtrarArticulos(String searchString, Long idSucursal, Integer limit, Long startId);
}
