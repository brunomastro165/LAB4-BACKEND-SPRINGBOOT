package com.example.buensaborback.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
public class ArticuloInsumo extends Articulo {

    private Double precioCompra;

    private Integer stockActual;

    private Integer stockMaximo;

    private Boolean esParaElaborar;
}
