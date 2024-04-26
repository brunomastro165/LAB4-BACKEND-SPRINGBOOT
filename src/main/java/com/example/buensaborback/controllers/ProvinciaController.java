package com.example.buensaborback.controllers;

import com.example.buensaborback.entities.Provincia;
import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.services.impl.ProvinciaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provincias")
@CrossOrigin("*")
public class ProvinciaController extends BaseControllerImpl<Provincia,Long, ProvinciaServiceImpl> {

    private static final Logger logger = LoggerFactory.getLogger(ProvinciaController.class);
    public ProvinciaController(ProvinciaServiceImpl service) {
        super(service);
    }

    @PutMapping("/asignarPais/{id}")
    public ResponseEntity<Provincia> asignarPais(@RequestParam Long paisId, @PathVariable Long id){
        logger.info("INICIO ASIGNAR PAIS A PROVINCIA");
        return ResponseEntity.ok(service.asignarPais(id,paisId));
    }

}
