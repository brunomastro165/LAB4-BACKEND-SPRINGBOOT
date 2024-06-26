package com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalle;

import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloManufacturadoDetalleDto extends BaseDto {
    private Integer cantidad;
    private ArticuloInsumoDto articuloInsumo;
}
