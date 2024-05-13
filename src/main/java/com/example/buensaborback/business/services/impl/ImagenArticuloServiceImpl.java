package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.ImagenArticuloService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import com.example.buensaborback.repositories.BaseRepository;
import com.example.buensaborback.repositories.ImagenArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagenArticuloServiceImpl extends BaseServiceImpl<ImagenArticulo, Long> implements ImagenArticuloService {
    @Autowired
    private ImagenArticuloRepository imagenArticuloRepository;

    public ImagenArticuloServiceImpl(BaseRepository<ImagenArticulo, Long> baseRepository, ImagenArticuloRepository imagenArticuloRepository) {
        super(baseRepository);
        this.imagenArticuloRepository = imagenArticuloRepository;
    }
}
