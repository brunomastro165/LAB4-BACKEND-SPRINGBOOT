package com.example.buensaborback.domain.dto.ArticuloManufacturado;


import com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleCreateDto;
import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.UnidadMedida.UnidadMedidaCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloManufacturadoCreateDto extends BaseDto {

    private String denominacion;
    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private Double precioVenta;
    private String preparacion;
    private UnidadMedidaCreateDto unidadMedida;
    private Set<ArticuloManufacturadoDetalleCreateDto> articuloManufacturadoDetalles;
}
