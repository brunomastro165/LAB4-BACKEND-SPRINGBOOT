package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ImagenArticuloFacadeImpl;
import com.example.buensaborback.domain.dtos.ImagenArticuloDTO;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/imagenArticulo")
public class ImagenArticuloController extends BaseControllerImpl<ImagenArticulo, ImagenArticuloDTO, Long, ImagenArticuloFacadeImpl> {
    public ImagenArticuloController(ImagenArticuloFacadeImpl facade) {
        super(facade);
    }
}
