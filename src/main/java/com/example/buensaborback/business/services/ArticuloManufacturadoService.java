package com.example.buensaborback.business.services;

import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;

import java.util.List;

public interface ArticuloManufacturadoService extends BaseService<ArticuloManufacturado, Long> {
    List<ArticuloManufacturado> getPorString(String searchString, Long idSucursal, Integer limit, Long startId);
}
