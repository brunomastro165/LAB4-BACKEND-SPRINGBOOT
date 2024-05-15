package com.example.buensaborback.domain.dto.Localidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalidadCreateDto {
    private String nombre;
    private Long idProvincia;
}
