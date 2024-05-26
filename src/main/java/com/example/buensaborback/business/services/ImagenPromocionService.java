package com.example.buensaborback.business.services;

import com.example.buensaborback.domain.dto.ImagenArticuloDto.ImagenArticuloDto;
import com.example.buensaborback.domain.dto.ImagenPromocion.ImagenPromocionDto;
import com.example.buensaborback.domain.entities.ImagenPromocion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ImagenPromocionService extends ImageService<ImagenPromocion, UUID> {
    List<ImagenPromocionDto> uploadImagesP(MultipartFile[] files, Long id);
}
