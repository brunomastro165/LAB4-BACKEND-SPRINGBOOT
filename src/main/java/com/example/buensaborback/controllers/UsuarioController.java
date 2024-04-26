package com.example.buensaborback.controllers;

import com.example.buensaborback.entities.Usuario;
import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.services.impl.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController extends BaseControllerImpl<Usuario,Long, UsuarioServiceImpl> {
    public UsuarioController(UsuarioServiceImpl service) {
        super(service);
    }

}
