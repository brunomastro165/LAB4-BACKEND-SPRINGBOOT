package com.example.buensaborback.business.services;

import com.example.buensaborback.domain.dto.ImagenArticuloDto.ImagenArticuloDto;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ImagenArticuloService extends ImageService<ImagenArticulo, UUID> {
    List<ImagenArticuloDto> uploadImagesA(MultipartFile[] files, Long id);
}
