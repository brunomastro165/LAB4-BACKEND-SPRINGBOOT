package com.example.buensaborback.business.services;

import com.example.buensaborback.domain.dto.ImagenEmpresa.ImagenEmpresaDto;
import com.example.buensaborback.domain.entities.ImagenEmpresa;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ImagenEmpresaService extends ImageService<ImagenEmpresa, UUID> {
    List<ImagenEmpresaDto> uploadImagesE(MultipartFile[] files, Long id);
}
