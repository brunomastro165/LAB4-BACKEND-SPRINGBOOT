package com.example.buensaborback.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class ArticuloManufacturadoDetalle extends Base{


    private Integer cantidad;

    @ManyToOne
    private ArticuloInsumo articuloInsumo;

}
