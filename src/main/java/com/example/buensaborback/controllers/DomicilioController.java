package com.example.buensaborback.controllers;

import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.entities.Domicilio;
import com.example.buensaborback.services.impl.DomicilioServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domicilios")
@CrossOrigin("*")
public class DomicilioController extends BaseControllerImpl<Domicilio,Long, DomicilioServiceImpl> {

    private static final Logger logger = LoggerFactory.getLogger(DomicilioController.class);
    public DomicilioController(DomicilioServiceImpl service) {
        super(service);
    }

    @PutMapping("/asignarLocalidad/{id}")
    public ResponseEntity<Domicilio> asignarLocalidad(@RequestParam Long localidadId, @PathVariable Long id){
        logger.info("INICIO ASIGNAR LOCALIDAD A DOMICILIO ");
        return ResponseEntity.ok(service.asignarLocalidad(id,localidadId));
    }
}
