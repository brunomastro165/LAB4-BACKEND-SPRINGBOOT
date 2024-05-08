package com.example.buensaborback.controllers;
/*
import com.example.buensaborback.entities.Empresa;
import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.services.impl.EmpresaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@CrossOrigin("*")
public class EmpresaController extends BaseControllerImpl<Empresa,Long, EmpresaServiceImpl> {
    private static final Logger logger = LoggerFactory.getLogger(EmpresaController.class);
    public EmpresaController(EmpresaServiceImpl service) {
        super(service);
    }

    @PutMapping("/asignarSucursales/{id}")
    public ResponseEntity<Empresa> asignarSucursales(@RequestParam List<Long> sucursalesIds, @PathVariable Long id){
        logger.info("INICIO ASIGNAR SUCURSALES A EMPRESA");
        return ResponseEntity.ok(service.asignarSucursales(id,sucursalesIds));
    }

    @PutMapping("/removerSucursales/{id}")
    public ResponseEntity<Empresa> removerSucursales(@RequestParam List<Long> sucursalesIds, @PathVariable Long id){
        logger.info("INICIO REMOVER SUCURSALES A EMPRESA");
        return ResponseEntity.ok(service.removerSucursales(id,sucursalesIds));
    }
}
*/