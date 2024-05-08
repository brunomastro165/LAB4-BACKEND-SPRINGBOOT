package com.example.buensaborback.services.impl;
/*
import com.example.buensaborback.services.IPromocionService;
import com.example.buensaborback.entities.Promocion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromocionServiceImpl extends BaseServiceImpl<Promocion,Long> implements IPromocionService {


    private static final Logger logger = LoggerFactory.getLogger(PromocionServiceImpl.class);
    @Autowired
    private IImagenService imagenService;

    @Override
    public Promocion create(Promocion entity) {
        if(entity.getArticulos().isEmpty()){
            String errMsg = "No se puede crear una promocion sin articulos";
            logger.error(errMsg);
            throw new RuntimeException(errMsg);
        }
        return super.create(entity);
    }

    @Override
    public Promocion asignarImagenes(Long id, List<Long> imagenesIds) {
        var promocion = getById(id);
        var imagenes = imagenService.getAll().stream()
                .filter(imagen -> imagenesIds.contains(imagen.getId()))
                .collect(Collectors.toList());
        imagenes.forEach(imagen -> promocion.getImagenes().add(imagen));
        return update(promocion);
    }

    @Override
    public Promocion removerImagenes(Long id, List<Long> imagenesIds) {
        var promocion = getById(id);
        var tempSet = new HashSet<Imagen>();
        promocion.getImagenes().forEach(imagen -> {
            if(imagenesIds.contains(imagen.getId())){
                tempSet.add(imagen);
            }
        });
        promocion.getImagenes().removeAll(tempSet);
        return update(promocion);
    }

}
*/