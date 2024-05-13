package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.ImagenClienteService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.ImagenCliente;
import com.example.buensaborback.repositories.BaseRepository;
import com.example.buensaborback.repositories.ImagenClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagenClienteServiceImpl extends BaseServiceImpl<ImagenCliente, Long> implements ImagenClienteService {
    @Autowired
    private ImagenClienteRepository imagenClienteRepository;

    public ImagenClienteServiceImpl(BaseRepository<ImagenCliente, Long> baseRepository, ImagenClienteRepository imagenClienteRepository) {
        super(baseRepository);
        this.imagenClienteRepository = imagenClienteRepository;
    }
}
