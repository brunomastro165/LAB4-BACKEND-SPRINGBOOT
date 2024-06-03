package com.example.buensaborback.business.services;

import com.example.buensaborback.domain.dto.ImagenEmpleado.ImagenEmpleadoDto;
import com.example.buensaborback.domain.entities.ImagenEmpleado;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ImagenEmpleadoService extends ImageService<ImagenEmpleado, UUID> {
    ImagenEmpleadoDto uploadImagesE(MultipartFile file, Long id);
}
