package com.example.buensaborback.controllers;

import com.example.buensaborback.entities.Factura;
import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.services.impl.FacturaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facturas")
@CrossOrigin("*")
public class FacturaController extends BaseControllerImpl<Factura,Long, FacturaServiceImpl>  {
    public FacturaController(FacturaServiceImpl service) {
        super(service);
    }
}
