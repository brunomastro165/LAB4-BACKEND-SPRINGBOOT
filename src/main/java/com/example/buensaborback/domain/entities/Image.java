package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
//@Audited
@SuperBuilder
public abstract class Image implements Serializable {

    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.UUID) // Generación automática del UUID como estrategia de generación
    @Column(name = "id") // Mapea este campo a la columna "id" en la tabla
    private UUID id; // Identificador único de la imagen

    @Column(name = "name_image") // Mapea este campo a la columna "name_image" en la tabla
    private String name; // Nombre de la imagen

    @Column(name = "url_image") // Mapea este campo a la columna "url_image" en la tabla
    private String url; // URL de la imagen en almacenamiento externo (por ejemplo, Cloudinary)
}
