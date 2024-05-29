package com.example.buensaborback.business.services;

import com.example.buensaborback.domain.dto.ImagenSucursal.ImagenSucursalDto;
import com.example.buensaborback.domain.entities.ImagenSucursal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ImagenSucursalService extends ImageService<ImagenSucursal, UUID> {
    List<ImagenSucursalDto> uploadImagesS(MultipartFile[] files, Long id);
}
