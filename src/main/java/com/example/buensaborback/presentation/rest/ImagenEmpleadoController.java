package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.domain.entities.ImagenArticulo;
import com.example.buensaborback.domain.entities.ImagenEmpleado;
import com.example.buensaborback.presentation.base.BaseImagenControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/imagenEmpleado")
public class ImagenEmpleadoController extends BaseImagenControllerImpl<ImagenEmpleado, UUID>  {
}
