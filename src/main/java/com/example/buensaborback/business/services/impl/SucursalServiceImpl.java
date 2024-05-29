package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.SucursalService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServiceImpl extends BaseServiceImpl<Sucursal, Long> implements SucursalService {
    @Autowired
    SucursalRepository sucursalRepository;

    @Override
    public List<Categoria> findCategoriasBySucursalId(Long idSucursal) {
        return sucursalRepository.findWithCategoriasById(idSucursal).getCategorias().stream().toList();
    }

    @Override
    public List<Promocion> findPromocionesBySucursalId(Long idSucursal) {
        return sucursalRepository.findWithPromocionesById(idSucursal).getPromociones().stream().toList();
    }
}
