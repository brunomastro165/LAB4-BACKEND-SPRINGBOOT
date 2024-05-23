package com.example.buensaborback.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data // Anotación Lombok para generar automáticamente getters, setters, equals, hashCode y toString
public class ImageModel {
    private MultipartFile file; // Objeto MultipartFile que representa el archivo de imagen
}
