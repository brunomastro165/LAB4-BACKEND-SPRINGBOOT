package com.example.buensaborback.domain.dto.PromocionDetalle;

import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import com.example.buensaborback.domain.entities.Promocion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PromocionDetalleDto extends BaseDto {
    private Integer cantidad;
    private ArticuloInsumoDto insumos;

    private ArticuloManufacturadoDto articulosManufacturados;
    private PromocionDto promocion;
}
