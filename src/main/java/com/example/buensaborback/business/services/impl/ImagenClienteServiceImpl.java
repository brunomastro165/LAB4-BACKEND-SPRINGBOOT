package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.ImagenClienteService;
import com.example.buensaborback.domain.entities.ImagenCliente;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Primary
public class ImagenClienteServiceImpl extends ImageServiceImpl<ImagenCliente, UUID> implements ImagenClienteService {
    @Override
    protected ImagenCliente createImageInstance() {
        return new ImagenCliente();
    }
}
