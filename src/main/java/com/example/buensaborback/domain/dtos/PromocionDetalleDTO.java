package com.example.buensaborback.domain.dtos;

import com.example.buensaborback.domain.entities.Articulo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PromocionDetalleDTO extends BaseDTO {
    private int cantidad;
    private Articulo articulo;
}
