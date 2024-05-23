package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.domain.entities.ImagenPromocion;
import com.example.buensaborback.presentation.base.BaseImagenControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/imagenPromocion")
public class ImagenPromocionController extends BaseImagenControllerImpl<ImagenPromocion, UUID> {
}