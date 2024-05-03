package com.example.buensaborback.entities;

import com.example.buensaborback.entities.Base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
@JsonIgnoreProperties({"articulo","cliende","promocion"})
public class Imagen extends Base {

    private String url;

    @ManyToOne
    @JoinColumn(name = "articuloId")
    private Articulo articulo;
    @OneToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "promocionId")
    private Promocion promocion;
    
}
