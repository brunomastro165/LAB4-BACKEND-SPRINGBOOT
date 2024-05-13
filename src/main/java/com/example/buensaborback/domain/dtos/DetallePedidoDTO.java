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
public class DetallePedidoDTO extends BaseDTO {

    private Integer cantidad;
    private Double subTotal;
    private Articulo articulo;
}
