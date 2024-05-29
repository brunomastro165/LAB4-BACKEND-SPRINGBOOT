package com.example.buensaborback.domain.dto.ArticuloManufacturado;

import com.example.buensaborback.domain.dto.Articulo.ArticuloShortDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloManufacturadoShortDto extends ArticuloShortDto {
    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private String preparacion;
}
