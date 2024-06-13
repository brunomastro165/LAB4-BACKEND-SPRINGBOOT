package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoEditDto;

import java.util.List;

public interface ArticuloManufacturadoFacade extends BaseFacade<ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoEditDto, Long> {
    List<ArticuloManufacturadoDto> getPorString(String searchString, Long idSucursal, Integer limit, Long startId);
}
