package com.example.buensaborback.business.services;

import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.entities.Categoria;

public interface CategoriaService extends BaseService<Categoria, Long> {
    Categoria addArticulo(Long idCategoria, Long idArticulo);

    Categoria addSubCategoria(Long idCategoria, Categoria subCategoriaToCreate);
}
