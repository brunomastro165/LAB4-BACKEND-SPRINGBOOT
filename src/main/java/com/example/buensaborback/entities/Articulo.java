package com.example.buensaborback.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
@SuperBuilder

//Genera UNA TABLA para cada CLASE que HEREDA de esta
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Articulo extends Base {

    protected String denominacion;
    protected Double precioVenta;

    @OneToMany
    @JoinColumn(name = "articulo_id")
    @Builder.Default
    private Set<Imagen> imagenes = new HashSet<>();

    @ManyToOne
    private UnidadMedida unidadMedida;

    public Articulo(String denominacion, Double precioVenta, UnidadMedida unidadMedida){
        this.denominacion = denominacion;
        this.precioVenta = precioVenta;
        this.unidadMedida = unidadMedida;
    }
}
