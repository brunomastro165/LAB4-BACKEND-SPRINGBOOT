package com.example.buensaborback.entities;

import com.example.buensaborback.entities.Articulo;
import com.example.buensaborback.entities.Base;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class DetallePedido extends Base {

    private Integer cantidad;
    private Double subTotal;

    @ManyToOne
    private Articulo articulo;

}
