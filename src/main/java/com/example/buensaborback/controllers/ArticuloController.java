package com.example.buensaborback.controllers;

import com.example.buensaborback.controllers.base.BaseController;
import com.example.buensaborback.entities.Articulo;
import com.example.buensaborback.entities.Base;
import com.example.buensaborback.services.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public interface ArticuloController <D extends Articulo, ID extends Serializable>{
    ResponseEntity<D> getById(ID id);
    ResponseEntity<List<D>> getAll();
    ResponseEntity<D> create(D entity);
    ResponseEntity<D> edit(D entity, ID id);
    ResponseEntity<?> deleteById(ID id);
}