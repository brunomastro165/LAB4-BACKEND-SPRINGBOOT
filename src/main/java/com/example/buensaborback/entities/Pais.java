package com.example.buensaborback.entities;

import com.example.buensaborback.entities.Base;
import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Pais extends Base {

    private String nombre;

}
