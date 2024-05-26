package com.example.buensaborback.business.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

// Interfaz que define los métodos para interactuar con Cloudinary
public interface CloudinaryService {

    // Método para subir un archivo a Cloudinary
    default String uploadFile(MultipartFile file) {
        return null; // Implementación por defecto que retorna null
    }

    // Método para eliminar una imagen de Cloudinary
    ResponseEntity<String> deleteImage(String publicId, UUID uuid);
}
