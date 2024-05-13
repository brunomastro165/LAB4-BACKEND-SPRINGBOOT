package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ImagenEmpleadoFacadeImpl;
import com.example.buensaborback.domain.dtos.ImagenEmpleadoDTO;
import com.example.buensaborback.domain.entities.ImagenEmpleado;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/imagenEmpleado")
public class ImagenEmpleadoController extends BaseControllerImpl<ImagenEmpleado, ImagenEmpleadoDTO, Long, ImagenEmpleadoFacadeImpl> {
    public ImagenEmpleadoController(ImagenEmpleadoFacadeImpl facade) {
        super(facade);
    }
}
