package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.ImagenEmpleadoService;
import com.example.buensaborback.domain.entities.ImagenEmpleado;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Primary
@Service
public class ImagenEmpleadoServiceImpl extends ImageServiceImpl<ImagenEmpleado, UUID> implements ImagenEmpleadoService {
    @Override
    protected ImagenEmpleado createImageInstance() {
        return new ImagenEmpleado();
    }
}
