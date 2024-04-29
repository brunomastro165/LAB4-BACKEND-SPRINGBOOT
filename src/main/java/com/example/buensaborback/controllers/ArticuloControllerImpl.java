package com.example.buensaborback.controllers;

import com.example.buensaborback.controllers.base.BaseController;
import com.example.buensaborback.entities.Articulo;
import com.example.buensaborback.entities.Base;
import com.example.buensaborback.services.impl.ArticuloServiceImpl;
import com.example.buensaborback.services.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class ArticuloControllerImpl<E extends Articulo, ID extends Serializable, S extends ArticuloServiceImpl<E,ID>> implements ArticuloController<E,ID> {

    private static final Logger logger = LoggerFactory.getLogger(com.example.buensaborback.controllers.ArticuloControllerImpl.class);
    protected S service;

    public ArticuloControllerImpl(S service) {
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
