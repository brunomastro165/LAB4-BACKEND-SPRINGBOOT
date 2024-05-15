package com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalle;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Insumo.ArticuloInsumoDto;
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
