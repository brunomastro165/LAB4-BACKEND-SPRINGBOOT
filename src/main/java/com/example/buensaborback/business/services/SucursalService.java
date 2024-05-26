package com.example.buensaborback.business.services;

import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.domain.entities.Sucursal;

import java.util.List;

public interface SucursalService extends BaseService<Sucursal, Long> {
    List<Categoria> findCategoriasBySucursalId(Long idSucursal);
    List<Promocion> findPromocionesBySucursalId(Long idSucursal);
}

