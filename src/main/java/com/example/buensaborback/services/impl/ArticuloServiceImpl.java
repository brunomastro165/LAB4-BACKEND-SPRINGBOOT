package com.example.buensaborback.services.impl;

import com.example.buensaborback.entities.Articulo;
import com.example.buensaborback.entities.Base;
import com.example.buensaborback.repositories.ArticuloRepository;
import com.example.buensaborback.repositories.BaseRepository;
import com.example.buensaborback.services.IArticuloService;
import com.example.buensaborback.services.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class ArticuloServiceImpl<E extends Articulo,ID extends Serializable> implements IArticuloService<E, ID> {

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired
    protected ArticuloRepository<E,ID> articuloRepository;

    @Override
    public E create(E entity) {
        var newEntity = articuloRepository.save(entity);
        logger.info("Creada entidad {}",newEntity);
        return newEntity;
    }

    @Override
    public E getById(ID id) {
        var optionalEntity = articuloRepository.findById(id);

        if (optionalEntity.isEmpty()){
            logger.error("No se encontro una entidad con el id " + id);
            throw new RuntimeException("No se encontro una entidad con el id " + id);
        }
        var entity = optionalEntity.get();
        logger.info("Obtenida entidad {}",entity);
        return entity;
    }

    @Override
    public List<E> getAll() {
        var entities = articuloRepository.findAll();
        logger.info("Obtenidas entidades {}",entities);
        return entities;
    }

    @Override
    public void deleteById(ID id) {
        var entity = getById(id);
        articuloRepository.deleteById(id);
        logger.info("Borrada entidad {}",entity);
    }

    @Override
    public E update(E entity) {
        var optionalEntity = articuloRepository.findById((ID) entity.getId());
        if (optionalEntity.isEmpty()){
            logger.error("No se encontro una entidad con el id " + entity.getId());
            throw new RuntimeException("No se encontro una entidad con el id " + entity.getId());
        }
        var newEntity = articuloRepository.save(entity);
        logger.info("Actualizada entidad {}",newEntity);
        return newEntity;
    }

}

