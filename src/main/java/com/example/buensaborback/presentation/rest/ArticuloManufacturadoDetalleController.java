package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ArticuloManufacturadoDetalleFacadeImpl;
import com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturadoDetalle;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/ArticuloManufacturadoDetalle")
public class ArticuloManufacturadoDetalleController extends BaseControllerImpl<ArticuloManufacturadoDetalle, ArticuloManufacturadoDetalleDto, ArticuloManufacturadoDetalleCreateDto, ArticuloManufacturadoDetalleCreateDto, Long, ArticuloManufacturadoDetalleFacadeImpl> {
    public ArticuloManufacturadoDetalleController(ArticuloManufacturadoDetalleFacadeImpl facade) {
        super(facade);
    }
}
