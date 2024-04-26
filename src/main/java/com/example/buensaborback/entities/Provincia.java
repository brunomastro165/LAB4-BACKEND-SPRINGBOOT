package com.example.buensaborback.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Provincia extends Base {

    private String nombre;

    @ManyToOne
    private Pais pais;

}
