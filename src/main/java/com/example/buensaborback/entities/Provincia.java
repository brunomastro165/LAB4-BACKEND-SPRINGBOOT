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
@JsonIgnoreProperties("pais")
public class Provincia extends Base {

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "paisId")
    private Pais pais;

}
