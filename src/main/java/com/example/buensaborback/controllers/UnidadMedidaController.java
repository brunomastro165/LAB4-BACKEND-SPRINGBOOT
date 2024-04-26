package com.example.buensaborback.controllers;

import com.example.buensaborback.entities.UnidadMedida;
import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.services.impl.UnidadMedidaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unidadesMedidas")
@CrossOrigin("*")
public class UnidadMedidaController extends BaseControllerImpl<UnidadMedida,Long, UnidadMedidaServiceImpl> {
    public UnidadMedidaController(UnidadMedidaServiceImpl service) {
        super(service);
    }
}
