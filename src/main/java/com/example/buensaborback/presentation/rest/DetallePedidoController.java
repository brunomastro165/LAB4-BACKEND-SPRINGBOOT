package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.DetallePedidoFacadeImpl;
import com.example.buensaborback.domain.dtos.DetallePedidoDTO;
import com.example.buensaborback.domain.entities.DetallePedido;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/detallePedido")
public class DetallePedidoController extends BaseControllerImpl<DetallePedido, DetallePedidoDTO, Long, DetallePedidoFacadeImpl> {
    public DetallePedidoController(DetallePedidoFacadeImpl facade) {
        super(facade);
    }
}
