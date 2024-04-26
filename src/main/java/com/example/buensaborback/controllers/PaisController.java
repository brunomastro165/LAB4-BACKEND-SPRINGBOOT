package com.example.buensaborback.controllers;

import com.example.buensaborback.entities.Pais;
import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.services.impl.PaisServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paises")
@CrossOrigin("*")
public class PaisController extends BaseControllerImpl<Pais,Long, PaisServiceImpl>{
    public PaisController(PaisServiceImpl service) {
        super(service);
    }

}
