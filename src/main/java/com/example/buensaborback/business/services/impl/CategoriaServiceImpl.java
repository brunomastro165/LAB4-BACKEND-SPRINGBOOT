package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.CategoriaService;
import com.example.buensaborback.business.services.SucursalService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.repositories.ArticuloInsumoRepository;
import com.example.buensaborback.repositories.ArticuloManufacturadoRepository;
import com.example.buensaborback.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl extends BaseServiceImpl<Categoria, Long> implements CategoriaService {
    @Autowired
    ArticuloRepository articuloRepository;

    @Autowired
    SucursalService sucursalService;
    @Autowired
    ArticuloInsumoRepository articuloInsumoFacade;
    @Autowired
    ArticuloManufacturadoRepository articuloManufacturadoFacade;


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

    public List<ArticuloManufacturado> getManufacturadoSubCategoria(Long idSub, String searchString) {
        Categoria categoria = getById(idSub);
        List<ArticuloManufacturado> manufacturados = new ArrayList<>();
        if (!categoria.getSubCategorias().isEmpty()) {
            manufacturados = categoria.getSubCategorias().stream()
                    .flatMap(c -> getManufacturadoSubCategoria(c.getId(), searchString).stream())
                    .collect(Collectors.toList());

        }
        var articulos = articuloManufacturadoFacade.getAll();
        List<ArticuloManufacturado> filteredArticulos;
        if (searchString == null || searchString == "") {
            filteredArticulos = articulos.stream()
                    .filter(articuloManufacturadoDto -> articuloManufacturadoDto.getCategoria().getId().equals(idSub))
                    .collect(Collectors.toList());
        } else {
            filteredArticulos = articulos.stream()
                    .filter(articuloManufacturadoDto -> articuloManufacturadoDto.getCategoria().getId().equals(idSub) && articuloManufacturadoDto.getDenominacion().toLowerCase().contains(searchString.toLowerCase()))
                    .collect(Collectors.toList());
        }

        for (ArticuloManufacturado articulo :
                filteredArticulos) {
            manufacturados.add(articulo);
        }
        return manufacturados;
    }

    @Override
    public List<ArticuloInsumo> getInsumoSubCategoria(Long idSub, String searchString) {
        Categoria categoria = getById(idSub);
        List<ArticuloInsumo> insumos = new ArrayList<>();
        if (!categoria.getSubCategorias().isEmpty()) {
            insumos = categoria.getSubCategorias().stream()
                    .flatMap(c -> getInsumoSubCategoria(c.getId(), searchString).stream())
                    .collect(Collectors.toList());
        }
        var articulos = articuloInsumoFacade.getAll();
        List<ArticuloInsumo> filteredArticulos;
        if (searchString == null || searchString == "") {
            filteredArticulos = articulos.stream()
                    .filter(articuloInsumoDto -> articuloInsumoDto.getCategoria().getId().equals(idSub))
                    .collect(Collectors.toList());
        } else {
            filteredArticulos = articulos.stream()
                    .filter(articuloInsumoDto -> articuloInsumoDto.getCategoria().getId().equals(idSub) && articuloInsumoDto.getDenominacion().toLowerCase().contains(searchString.toLowerCase()))
                    .collect(Collectors.toList());
        }
        for (ArticuloInsumo articulo :
                filteredArticulos) {
            insumos.add(articulo);
        }
        return insumos;
    }

}
