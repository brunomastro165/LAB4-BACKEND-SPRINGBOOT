package com.example.buensaborback.domain.dto.ArticuloManufacturado;


import com.example.buensaborback.domain.dto.Articulo.ArticuloCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloManufacturadoCreateDto extends ArticuloCreateDto {

    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private String preparacion;
    private Set<ArticuloManufacturadoDetalleCreateDto> articuloManufacturadoDetalles;
}
