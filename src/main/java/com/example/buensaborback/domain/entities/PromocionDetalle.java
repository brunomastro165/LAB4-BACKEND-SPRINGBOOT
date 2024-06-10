package com.example.buensaborback.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@ToString
//@Audited
public class PromocionDetalle extends Base {
    private Integer cantidad;

    @ManyToOne
    private Articulo articulo;
    @ManyToOne
    private Promocion promocion;
}
