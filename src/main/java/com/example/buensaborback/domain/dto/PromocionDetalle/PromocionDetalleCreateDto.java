package com.example.buensaborback.domain.dto.PromocionDetalle;

import com.example.buensaborback.domain.dto.Articulo.ArticuloDto;
import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PromocionDetalleCreateDto extends BaseDto {
    private ArticuloDto articuloDto;
}
