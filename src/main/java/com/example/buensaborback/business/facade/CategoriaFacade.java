package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaCreateDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaEditDto;

import java.util.List;

public interface CategoriaFacade extends BaseFacade<CategoriaDto, CategoriaCreateDto, CategoriaEditDto, Long> {
    CategoriaDto addInsumo(Long idCategoria, Long idInsumo);

    CategoriaDto addManufacturado(Long idCategoria, Long idManufacturado);

    CategoriaDto addSubCategoria(Long idCategoria, CategoriaCreateDto subCategoria);

    List<ArticuloManufacturadoDto> getManufacturadoSubCategoria(Long idSub, String searchString);

    List<ArticuloInsumoDto> getInsumoSubCategoria(Long idSub, String searchString);
}
