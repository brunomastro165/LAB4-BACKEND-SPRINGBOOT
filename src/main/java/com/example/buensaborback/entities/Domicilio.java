package com.example.buensaborback.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
@JsonIgnoreProperties({"localidad"})
public class Domicilio extends Base {

    private String calle;
    private Integer numero;
    private Integer cp;
    private Integer piso;
    private Integer nroDpto;

    @ManyToOne
    @JoinColumn(name="localidadId")
    private Localidad localidad;

}
