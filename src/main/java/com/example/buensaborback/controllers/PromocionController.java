package com.example.buensaborback.controllers;

import com.example.buensaborback.entities.Promocion;
import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.services.impl.PromocionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promociones")
@CrossOrigin("*")
public class PromocionController extends BaseControllerImpl<Promocion, Long, PromocionServiceImpl> {

    private static final Logger logger = LoggerFactory.getLogger(PromocionController.class);
    public PromocionController(PromocionServiceImpl service) {
        super(service);
    }

    @PutMapping("/asignarImagenes/{id}")
    public ResponseEntity<Promocion> asignarImagenes(@RequestParam List<Long> imagenesIds, @PathVariable Long id){
        logger.info("INICIO ASIGNAR IMAGENES A PROMOCION");
        return ResponseEntity.ok(service.asignarImagenes(id,imagenesIds));
    }

    @PutMapping("/removerImagenes/{id}")
    public ResponseEntity<Promocion> removerImagenes(@RequestParam List<Long> imagenesIds, @PathVariable Long id){
        logger.info("INICIO REMOVER IMAGENES A PROMOCION");
        return ResponseEntity.ok(service.removerImagenes(id,imagenesIds));
    }
}
