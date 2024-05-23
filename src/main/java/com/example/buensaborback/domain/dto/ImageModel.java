package com.example.buensaborback.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data // Anotación Lombok para generar automáticamente getters, setters, equals, hashCode y toString
@AllArgsConstructor
public class ImageModel {
    private String name;
    private String url; // Objeto MultipartFile que representa el archivo de imagen
}
