package com.example.buensaborback.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
@JsonIgnoreProperties({"articulos","categoriaPadre"})
public class Categoria extends Base{

    private String denominacion;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "categoria")
    @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "categoriaId")
    private Categoria categoriaPadre;

    @OneToMany(mappedBy = "categoriaPadre", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Categoria> subCategorias = new HashSet<>();

}
