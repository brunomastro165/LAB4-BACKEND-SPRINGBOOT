package com.example.buensaborback.controllers;
/*
import com.example.buensaborback.entities.Cliente;
import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.services.impl.ClienteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteController extends BaseControllerImpl<Cliente,Long, ClienteServiceImpl> {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    public ClienteController(ClienteServiceImpl service) {
        super(service);
    }

    @PutMapping("/asignarUsuario/{id}")
    public ResponseEntity<Cliente> asignarUsuario(@RequestParam Long usuarioId, @PathVariable Long id){
        logger.info("INICIO ASIGNAR USUARIO A CLIENTE");
        return ResponseEntity.ok(service.asignarUsuario(id,usuarioId));
    }

    @PutMapping("/asignarImagen/{id}")
    public ResponseEntity<Cliente> asignarImagen(@RequestParam Long imagenId, @PathVariable Long id){
        logger.info("INICIO ASIGNAR IMAGEN A CLIENTE");
        return ResponseEntity.ok(service.asignarImagen(id,imagenId));
    }

    @PutMapping("/asignarDomicilios/{id}")
    public ResponseEntity<Cliente> asignarDomicilios(@RequestParam List<Long> domiciliosIds, @PathVariable Long id){
        logger.info("INICIO ASIGNAR DOMICILIOS A CLIENTE");
        return ResponseEntity.ok(service.asignarDomicilios(id,domiciliosIds));
    }

    @PutMapping("/removerDomicilios/{id}")
    public ResponseEntity<Cliente> removerDomicilios(@RequestParam List<Long> domiciliosIds, @PathVariable Long id){
        logger.info("INICIO REMOVER DOMICILIOS A CLIENTE");
        return ResponseEntity.ok(service.removerDomicilios(id,domiciliosIds));
    }

    @PutMapping("/asignarPedidos/{id}")
    public ResponseEntity<Cliente> asignarPedidos(@RequestParam List<Long> pedidosIds, @PathVariable Long id){
        logger.info("INICIO ASIGNAR PEDIDOS A CLIENTE");
        return ResponseEntity.ok(service.asignarPedidos(id,pedidosIds));
    }

    @PutMapping("/removerPedidos/{id}")
    public ResponseEntity<Cliente> removerPedidos(@RequestParam List<Long> pedidosIds, @PathVariable Long id){
        logger.info("INICIO REMOVER PEDIDOS A CLIENTE");
        return ResponseEntity.ok(service.removerPedidos(id,pedidosIds));
    }

}
*/