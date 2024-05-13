package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.ImagenPromocionService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.ImagenPromocion;
import com.example.buensaborback.repositories.BaseRepository;
import com.example.buensaborback.repositories.ImagenPromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagenPromocionServiceImpl extends BaseServiceImpl<ImagenPromocion, Long> implements ImagenPromocionService {
    @Autowired
    private ImagenPromocionRepository imagenPromocionRepository;

    public ImagenPromocionServiceImpl(BaseRepository<ImagenPromocion, Long> baseRepository, ImagenPromocionRepository imagenPromocionRepository) {
        super(baseRepository);
        this.imagenPromocionRepository = imagenPromocionRepository;
    }
}
