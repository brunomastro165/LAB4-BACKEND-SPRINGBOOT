package com.example.buensaborback.domain.dtos;

import com.example.buensaborback.domain.entities.ArticuloInsumo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticuloManufacturadoDetalleDTO extends BaseDTO {

    private Integer cantidad;
    private ArticuloInsumo articuloInsumo;
}
