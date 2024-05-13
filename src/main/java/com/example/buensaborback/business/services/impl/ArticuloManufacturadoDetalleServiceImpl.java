package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.ArticuloManufacturadoDetalleService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.ArticuloManufacturadoDetalle;
import com.example.buensaborback.repositories.ArticuloManufacturadoDetalleRepository;
import com.example.buensaborback.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloManufacturadoDetalleServiceImpl extends BaseServiceImpl<ArticuloManufacturadoDetalle, Long> implements ArticuloManufacturadoDetalleService {

    @Autowired
    private ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;

    public ArticuloManufacturadoDetalleServiceImpl(BaseRepository<ArticuloManufacturadoDetalle, Long> baseRepository, ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository) {
        super(baseRepository);
        this.articuloManufacturadoDetalleRepository = articuloManufacturadoDetalleRepository;

    }

}
