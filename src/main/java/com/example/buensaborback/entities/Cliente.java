package com.example.buensaborback.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"usuario","imagen","pedido","domicilios"})
public class Cliente extends Base {

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    @OneToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    private Imagen imagen;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    @Builder.Default
    private Set<Pedido> pedidos = new HashSet<>();
    //Revisar


    @ManyToMany
    @JoinTable(name = "cliente_domicilio",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "domicilio_id"))
    @Builder.Default
    private Set<Domicilio> domicilios = new HashSet<>();

}
