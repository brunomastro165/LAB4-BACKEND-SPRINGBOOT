package com.example.buensaborback.domain.dtos.shortDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticuloManufacturadoShortDTO extends ArticuloShortDTO {
    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private String preparacion;
}
