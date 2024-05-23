package com.example.buensaborback.business.facade;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ImagenArticuloFacade {
    String uploadImages(MultipartFile[] files, Long idArticulo);

    String deleteImage(String publicId, UUID id);
}
