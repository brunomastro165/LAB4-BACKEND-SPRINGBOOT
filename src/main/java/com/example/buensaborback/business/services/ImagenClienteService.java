package com.example.buensaborback.business.services;

import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteDto;
import com.example.buensaborback.domain.entities.ImagenCliente;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ImagenClienteService extends ImageService<ImagenCliente, UUID> {
    ImagenClienteDto uploadImagesC(MultipartFile file, Long id);
}
