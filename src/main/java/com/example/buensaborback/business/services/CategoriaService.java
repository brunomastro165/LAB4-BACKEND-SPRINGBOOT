package com.example.buensaborback.business.services;

import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.entities.Categoria;

public interface CategoriaService extends BaseService<Categoria, Long> {
    public Categoria addArticulo(Long idCategoria, Long idArticulo);
    public Categoria addSubCategoria(Long idCategoria, Categoria subCategoriaToCreate);
}
