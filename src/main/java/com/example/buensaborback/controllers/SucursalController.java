package com.example.buensaborback.controllers;
/*
import com.example.buensaborback.entities.Sucursal;
import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.services.impl.SucursalServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursales")
@CrossOrigin("*")
public class SucursalController extends BaseControllerImpl<Sucursal,Long, SucursalServiceImpl> {

    private static final Logger logger = LoggerFactory.getLogger(SucursalController.class);
    public SucursalController(SucursalServiceImpl service) {
        super(service);
    }

    @PutMapping("/asignarDomicilio/{id}")
    public ResponseEntity<Sucursal> asignarDomicilio(@RequestParam Long domicilioId, @PathVariable Long id){
        logger.info("INICIO ASIGNAR DOMICILIO A SUCURSAL");
        return ResponseEntity.ok(service.asignarDomicilio(id,domicilioId));
    }

    @PutMapping("/asignarCategorias/{id}")
    public ResponseEntity<Sucursal> asignarCategorias(@RequestParam List<Long> categoriasIds, @PathVariable Long id){
        logger.info("INICIO ASIGNAR CATEGORIAS A SUCURSAL");
        return ResponseEntity.ok(service.asignarCategorias(id,categoriasIds));
    }

    @PutMapping("/removerCategorias/{id}")
    public ResponseEntity<Sucursal> removerCategorias(@RequestParam List<Long> categoriasIds, @PathVariable Long id){
        logger.info("INICIO REMOVER CATEGORIAS A SUCURSAL");
        return ResponseEntity.ok(service.removerCategorias(id,categoriasIds));
    }

    @PutMapping("/asignarPromociones/{id}")
    public ResponseEntity<Sucursal> asignarPromociones(@RequestParam List<Long> promocionesIds, @PathVariable Long id){
        logger.info("INICIO ASIGNAR PROMOCIONES A SUCURSAL");
        return ResponseEntity.ok(service.asignarPromociones(id,promocionesIds));
    }

    @PutMapping("/removerPromociones/{id}")
    public ResponseEntity<Sucursal> removerPromociones(@RequestParam List<Long> promocionesIds, @PathVariable Long id){
        logger.info("INICIO REMOVER PROMOCIONES A SUCURSAL");
        return ResponseEntity.ok(service.removerPromociones(id,promocionesIds));
    }

}
*/