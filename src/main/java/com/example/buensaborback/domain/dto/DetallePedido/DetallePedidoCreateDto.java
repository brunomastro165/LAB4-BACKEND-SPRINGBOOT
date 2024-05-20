package com.example.buensaborback.domain.dto.DetallePedido;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.entities.Articulo;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoCreateDto extends BaseDto {
    private Integer cantidad;
    private Double subTotal;


    private Articulo articulo;
}
