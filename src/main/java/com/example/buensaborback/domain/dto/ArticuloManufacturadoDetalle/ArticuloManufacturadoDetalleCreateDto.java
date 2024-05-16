package com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalle;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.Insumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloManufacturadoDetalleCreateDto extends BaseDto {
    private Integer cantidad;
    private ArticuloInsumoCreateDto articuloInsumo;
}
