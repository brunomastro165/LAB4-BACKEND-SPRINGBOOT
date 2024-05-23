package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.domain.entities.ImagenCliente;
import com.example.buensaborback.presentation.base.BaseImagenControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/imagenCliente")
public class ImagenClienteController extends BaseImagenControllerImpl<ImagenCliente, UUID> {
}
