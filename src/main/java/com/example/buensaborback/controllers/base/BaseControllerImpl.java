package com.example.buensaborback.controllers.base;

import com.example.buensaborback.entities.Base;
import com.example.buensaborback.services.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class BaseControllerImpl<E extends Base, ID extends Serializable, S extends BaseServiceImpl<E,ID>> implements BaseController<E,ID>  {

    private static final Logger logger = LoggerFactory.getLogger(BaseControllerImpl.class);
    protected S service;

    public BaseControllerImpl(S service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<E> getById(@PathVariable ID id) {
        logger.info("INICIO GET BY ID {}", id);
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<E>> getAll() {
        logger.info("INICIO GET ALL");
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping()
    public ResponseEntity<E> create(@RequestBody E entity) {
        logger.info("INICIO CREATE {}", entity.getClass());
        return ResponseEntity.ok(service.create(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<E> edit(@RequestBody E entity, @PathVariable ID id) {
        logger.info("INICIO EDIT {}", entity.getClass());
        return ResponseEntity.ok(service.update(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ID id) {
        logger.info("INICIO DELETE BY ID");
        service.deleteById(id);
        return ResponseEntity.ok(null);
    }

}
