package com.example.buensaborback.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
//@Audited
public class DetallePedido extends Base {
    private Integer cantidad;
    private Double subTotal;

    @ManyToOne
    private Articulo articulo;

}
