package com.example.buensaborback.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
@JsonIgnoreProperties({"domicilio","empresa","categorias","promociones"})
public class Sucursal extends Base {

    private String nombre;

    private LocalTime horarioApertura;

    private LocalTime horarioCierre;

    @OneToOne
    @JoinColumn(name = "domicilioId")
    private Domicilio domicilio;

    @ManyToOne
    @JoinColumn(name = "empresaId")
    private Empresa empresa;

    @ManyToMany
    @JoinTable(name = "sucursal_categoria",
            joinColumns = @JoinColumn(name = "sucursal_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    @Builder.Default
    private Set<Categoria> categorias = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "sucursal_promocion",
            joinColumns = @JoinColumn(name = "sucursal_id"),
            inverseJoinColumns = @JoinColumn(name = "promocion_id"))
    @Builder.Default
    private Set<Promocion> promociones = new HashSet<>();
}
