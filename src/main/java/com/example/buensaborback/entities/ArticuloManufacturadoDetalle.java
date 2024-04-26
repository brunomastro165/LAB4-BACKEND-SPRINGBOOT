package com.example.buensaborback.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    @JoinColumn(name = "articuloInsumoId")
    private ArticuloInsumo articuloInsumo;


    @ManyToOne
    @JoinColumn(name = "articuloManufacturadoId")
    private ArticuloManufacturado articuloManufacturado;

}
