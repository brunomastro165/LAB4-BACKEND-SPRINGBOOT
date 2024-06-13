package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoEditDto;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.ArticuloInsumo;

import java.util.List;

public interface ArticuloInsumoFacade extends BaseFacade<ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoEditDto, Long> {
    List<Articulo> getAllArticulos(String searchString , Long idSucursal, Integer limit, Long startId);
    List<ArticuloInsumoDto> filtrarArticulos(String searchString, Long idSucursal, Integer limit, Long startId);
}
