package com.example.buensaborback.controllers;

import com.example.buensaborback.entities.Imagen;
import com.example.buensaborback.controllers.base.BaseControllerImpl;
import com.example.buensaborback.services.impl.ImagenServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagenes")
@CrossOrigin("*")
public class ImagenController extends BaseControllerImpl<Imagen,Long, ImagenServiceImpl> {
    public ImagenController(ImagenServiceImpl service) {
        super(service);
    }
}
