package com.example.buensaborback.controllers;

import com.example.buensaborback.entities.Pedido;
import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.services.impl.PedidoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin("*")
public class PedidoController extends BaseControllerImpl<Pedido,Long, PedidoServiceImpl> {
    public PedidoController(PedidoServiceImpl service) {
        super(service);
    }


}
