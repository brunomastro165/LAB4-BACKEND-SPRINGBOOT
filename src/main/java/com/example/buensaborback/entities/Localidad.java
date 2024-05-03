package com.example.buensaborback.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
@JsonIgnoreProperties({"provincia"})
public class Localidad extends Base {

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "provinciaId")
    private Provincia provincia;

}
