package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ImagenArticuloFacade;
import com.example.buensaborback.business.services.ImagenArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
public class ImagenArticuloFacadeImp implements ImagenArticuloFacade {

    @Autowired
    private ImagenArticuloService imagenArticuloService;


    @Override
    public String uploadImages(MultipartFile[] files, Long idArticulo) {
        return imagenArticuloService.uploadImages(files, idArticulo).getBody();
    }

    @Override
    public String deleteImage(String publicId, UUID id) {
        return imagenArticuloService.deleteImage(publicId, id).getBody();
    }
}
