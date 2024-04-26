package com.example.buensaborback.controllers;

import com.example.buensaborback.entities.Localidad;
import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.services.impl.LocalidadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/localidades")
@CrossOrigin("*")
public class LocalidadController extends BaseControllerImpl<Localidad,Long, LocalidadServiceImpl> {
    private static final Logger logger = LoggerFactory.getLogger(LocalidadController.class);
    public LocalidadController(LocalidadServiceImpl service) {
        super(service);
    }

    @PutMapping("/asignarProvincia/{id}")
    public ResponseEntity<Localidad> asignarProvincia(@RequestParam Long provinciaId, @PathVariable Long id){
        logger.info("INICIO ASIGNAR PROVINCIA A LOCALIDAD");
        return ResponseEntity.ok(service.asignarProvincia(id,provinciaId));
    }

}
