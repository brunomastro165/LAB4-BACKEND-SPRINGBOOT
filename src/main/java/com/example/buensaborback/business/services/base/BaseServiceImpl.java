package com.example.buensaborback.business.services.base;

import com.example.buensaborback.domain.entities.Base;
import com.example.buensaborback.repositories.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;


@Service
public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired
    protected BaseRepository<E, ID> baseRepository;

    @Override
    public E create(E request) {
        System.out.println("Estoy en service");
        System.out.println(request);
        System.out.println(request);
        var newEntity = baseRepository.save(request);
        logger.info("Creada entidad {}", newEntity);
        return newEntity;
    }

    @Override
    public E getById(ID id) {
        var entity = baseRepository.getById(id);
        logger.info("Obtenida entidad {}", entity);
        return entity;
    }

    @Override
    public List<E> getAll() {
        var entities = baseRepository.getAll();
        logger.info("Obtenidas entidades {}", entities);
        return entities;
    }

    @Override
    public void deleteById(ID id) {
        var entity = getById(id);
        baseRepository.delete(entity);
        logger.info("Borrada logicamente entidad {}", entity);
    }
    @Override
    public void activateById(ID id) {
        var entity = baseRepository.getEliminadoById(id);
        entity.setEliminado(false);
        baseRepository.save(entity);
        logger.info("Activada logicamente entidad {}", entity);
    }

    @Override
    public E update(E request, ID id) {
        var optionalEntity = baseRepository.findById((ID) request.getId());
        if (optionalEntity.isEmpty()) {
            logger.error("No se encontro una entidad con el id " + request.getId());
            throw new RuntimeException("No se encontro una entidad con el id " + request.getId());
        }
        var newEntity = baseRepository.save(request);
        logger.info("Actualizada entidad {}", newEntity);
        return newEntity;
    }
}
