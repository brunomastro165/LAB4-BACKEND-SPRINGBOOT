package com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalle;

import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloManufacturadoDetalleCreateDto {
    private Integer cantidad;
    private ArticuloInsumoCreateDto ArticuloInsumo;
}