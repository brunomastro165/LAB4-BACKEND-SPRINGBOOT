package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.ImagenEmpleadoService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.ImagenEmpleado;
import com.example.buensaborback.repositories.BaseRepository;
import com.example.buensaborback.repositories.ImagenEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagenEmpleadoServiceImpl extends BaseServiceImpl<ImagenEmpleado, Long> implements ImagenEmpleadoService {
    @Autowired
    private ImagenEmpleadoRepository imagenEmpleadoRepository;

    public ImagenEmpleadoServiceImpl(BaseRepository<ImagenEmpleado, Long> baseRepository, ImagenEmpleadoRepository imagenEmpleadoRepository) {
        super(baseRepository);
        this.imagenEmpleadoRepository = imagenEmpleadoRepository;
    }
}
