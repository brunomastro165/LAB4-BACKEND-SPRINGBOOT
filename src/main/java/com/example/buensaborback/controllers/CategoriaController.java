package com.example.buensaborback.controllers;
/*
import com.example.buensaborback.entities.Categoria;
import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.services.impl.CategoriaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController extends BaseControllerImpl<Categoria,Long, CategoriaServiceImpl> {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaController.class);
    public CategoriaController(CategoriaServiceImpl service) {
        super(service);
    }

    @PutMapping("/asignarSubcategorias/{id}")
    public ResponseEntity<Categoria> asignarSubcategorias(@RequestParam List<Long> subcategoriasIds, @PathVariable Long id){
        logger.info("INICIO ASIGNAR SUBCATEGORIAS A CATEGORIAS");
        return ResponseEntity.ok(service.asignarSubcategorias(id,subcategoriasIds));
    }

    @PutMapping("/removerSubcategorias/{id}")
    public ResponseEntity<Categoria> removerSubcategorias(@RequestParam List<Long> subcategoriasIds, @PathVariable Long id){
        logger.info("INICIO REMOVER SUBCATEGORIAS A CATEGORIAS");
        return ResponseEntity.ok(service.removerSubcategorias(id,subcategoriasIds));
    }

    @PutMapping("/asignarArticulos/{id}")
    public ResponseEntity<Categoria> asignarArticulos(@RequestParam List<Long> articulosIds, @PathVariable Long id){
        logger.info("INICIO ASIGNAR ARTICULOS A CATEGORIAS");
        return ResponseEntity.ok(service.asignarArticulos(id,articulosIds));
    }

    @PutMapping("/removerArticulos/{id}")
    public ResponseEntity<Categoria> removerArticulos(@RequestParam List<Long> articulosIds, @PathVariable Long id){
        logger.info("INICIO REMOVER ARTICULOS A CATEGORIAS");
        return ResponseEntity.ok(service.removerArticulos(id,articulosIds));
    }

}
*/