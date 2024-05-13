package com.example.buensaborback.domain.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Provincia extends Base {
    private String nombre;

    @ManyToOne
    private Pais pais;

}
