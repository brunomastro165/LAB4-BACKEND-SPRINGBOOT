package com.example.buensaborback.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
//@Inheritance(strategy = InheritanceType.JOINED)
//Genera UNA TABLA para cada CLASE que HEREDA de esta
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
//Le cambié el abstract
public abstract class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    protected String denominacion;
    protected Double precioVenta;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "articulo")
    @Builder.Default
    private Set<Imagen> imagenes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "unidadMedidaId")
    private UnidadMedida unidadMedida;

    @ManyToOne
    @JoinColumn(name = "categoriaId")
    private Categoria categoria;

    public Articulo(String denominacion, Double precioVenta, UnidadMedida unidadMedida){
        this.denominacion = denominacion;
        this.precioVenta = precioVenta;
        this.unidadMedida = unidadMedida;
    }
}
