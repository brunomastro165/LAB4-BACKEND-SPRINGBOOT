package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ImagenClienteFacadeImpl;
import com.example.buensaborback.domain.dtos.ImagenClienteDTO;
import com.example.buensaborback.domain.entities.ImagenCliente;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/imagenCliente")
public class ImagenClienteController extends BaseControllerImpl<ImagenCliente, ImagenClienteDTO, Long, ImagenClienteFacadeImpl> {
    public ImagenClienteController(ImagenClienteFacadeImpl facade) {
        super(facade);
    }
}
