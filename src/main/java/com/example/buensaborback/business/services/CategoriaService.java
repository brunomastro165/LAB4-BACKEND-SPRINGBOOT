package com.example.buensaborback.business.services;

import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Categoria;

import java.util.List;

public interface CategoriaService extends BaseService<Categoria, Long> {
    Categoria addArticulo(Long idCategoria, Long idArticulo);

    Categoria addSubCategoria(Long idCategoria, Categoria subCategoriaToCreate);

    List<ArticuloManufacturado> getManufacturadoSubCategoria(Long idSub, String searchString);

    List<ArticuloInsumo> getInsumoSubCategoria(Long idSub, String searchString);
}
