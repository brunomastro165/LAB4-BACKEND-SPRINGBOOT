package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.ImagenArticuloService;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Primary
public class ImagenArticuloServiceImpl extends ImageServiceImpl<ImagenArticulo, UUID> implements ImagenArticuloService {
    @Override
    protected ImagenArticulo createImageInstance() {
        return new ImagenArticulo();
    }
}
