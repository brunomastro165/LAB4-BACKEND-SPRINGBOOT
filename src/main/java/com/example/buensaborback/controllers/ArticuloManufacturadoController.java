package com.example.buensaborback.controllers;

import com.example.buensaborback.entities.ArticuloManufacturado;
import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.services.impl.ArticuloManufactuadoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulosManufacturados")
@CrossOrigin("*")
public class ArticuloManufacturadoController extends ArticuloControllerImpl<ArticuloManufacturado,Long, ArticuloManufactuadoServiceImpl> {

    private static final Logger logger = LoggerFactory.getLogger(ArticuloManufacturadoController.class);
    public ArticuloManufacturadoController(ArticuloManufactuadoServiceImpl service) {
        super(service);
    }

    @PutMapping("/asignarImagenes/{id}")
    public ResponseEntity<ArticuloManufacturado> asignarImagenes(@RequestParam List<Long> imagenesIds, @PathVariable Long id){
        logger.info("INICIO ASIGNAR IMAGENES A ARTICULO MANUFACTURADO");
        return ResponseEntity.ok(service.asignarImagenes(id,imagenesIds));
    }

    @PutMapping("/removerImagenes/{id}")
    public ResponseEntity<ArticuloManufacturado> removerImagenes(@RequestParam List<Long> imagenesIds, @PathVariable Long id){
        logger.info("INICIO REMOVER IMAGENES A ARTICULO MANUFACTURADO");
        return ResponseEntity.ok(service.removerImagenes(id,imagenesIds));
    }

    @PutMapping("/asignarUnidadMedida/{id}")
    public ResponseEntity<ArticuloManufacturado> asignarUnidadMedida(@RequestParam Long unidadMedidaId, @PathVariable Long id){
        logger.info("INICIO ASIGNAR DOMICILIO A SUCURSAL");
        return ResponseEntity.ok(service.asignarUnidadMedida(id,unidadMedidaId));
    }

}
