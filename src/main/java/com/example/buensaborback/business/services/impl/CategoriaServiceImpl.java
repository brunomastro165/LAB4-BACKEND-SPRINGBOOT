package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.CategoriaService;
import com.example.buensaborback.business.services.SucursalService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoriaServiceImpl extends BaseServiceImpl<Categoria, Long> implements CategoriaService {
    @Autowired
    ArticuloRepository articuloRepository;

    @Autowired
    SucursalService sucursalService;

    @Override
    public Categoria addArticulo(Long idCategoria, Long idArticulo) {
        var categoria = baseRepository.getById(idCategoria);
        var articulo = articuloRepository.getById(idArticulo);
        categoria.getArticulos().add(articulo);
        return baseRepository.save(categoria);
    }

    @Override
    public Categoria addSubCategoria(Long idCategoria, Categoria subCategoriaToCreate) {
        var categoria = baseRepository.getById(idCategoria);
        create(subCategoriaToCreate);
        categoria.getSubCategorias().add(subCategoriaToCreate);
        return baseRepository.save(categoria);
    }

    @Override
    public Categoria create(Categoria request) {
        // Se obtienen las sucursales asociadas a la categoría.
        Set<Sucursal> sucursales = request.getSucursales();
        // Asignamos la categoría a cada sucursal y las guardamos
        var entitySaved = baseRepository.save(request);
        sucursales.stream()
                .map(sucursal -> {
                    sucursal.getCategorias().add(request);
                    return sucursal;
                })
                .forEach(sucursalService::create); // Suponiendo que sucursalService tiene un método save para guardar sucursales
        return entitySaved;
    }
}
