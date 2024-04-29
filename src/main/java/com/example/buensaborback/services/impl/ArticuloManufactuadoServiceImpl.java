package com.example.buensaborback.services.impl;

import com.example.buensaborback.entities.Articulo;
import com.example.buensaborback.services.IArticuloManufacturadoService;
import com.example.buensaborback.services.IImagenService;
import com.example.buensaborback.services.IUnidadMedidaService;
import com.example.buensaborback.entities.ArticuloManufacturado;
import com.example.buensaborback.entities.Imagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticuloManufactuadoServiceImpl extends ArticuloServiceImpl<ArticuloManufacturado,Long> implements IArticuloManufacturadoService {
    @Autowired
    private IImagenService imagenService;
    @Autowired
    private IUnidadMedidaService unidadMedidaService;

    @Override
    public ArticuloManufacturado asignarImagenes(Long id, List<Long> imagenesIds) {
        var articulo = getById(id);
        var imagenes = imagenService.getAll().stream()
                .filter(imagen -> imagenesIds.contains(imagen.getId()))
                .collect(Collectors.toList());
        imagenes.forEach(imagen -> articulo.getImagenes().add(imagen));
        return update(articulo);
    }

    @Override
    public ArticuloManufacturado removerImagenes(Long id, List<Long> imagenesIds) {
        var articulo = getById(id);
        var tempSet = new HashSet<Imagen>();
        articulo.getImagenes().forEach(imagen -> {
            if(imagenesIds.contains(imagen.getId())){
                tempSet.add(imagen);
            }
        });
        articulo.getImagenes().removeAll(tempSet);
        return update(articulo);
    }

    @Override
    public ArticuloManufacturado asignarUnidadMedida(Long id, Long unidadMedidaId) {
        var articulo = getById(id);
        var unidadMedida = unidadMedidaService.getById(unidadMedidaId);
        articulo.setUnidadMedida(unidadMedida);
        return update(articulo);
    }
}
