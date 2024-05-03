package com.example.buensaborback.entities;

import com.example.buensaborback.entities.Articulo;
import com.example.buensaborback.entities.Base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
@JsonIgnoreProperties({"articulo","pedidoId"})
public class DetallePedido extends Base {

    private Integer cantidad;
    private Double subTotal;

    @ManyToOne
    @JoinColumn(name = "articuloId")
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;

}
