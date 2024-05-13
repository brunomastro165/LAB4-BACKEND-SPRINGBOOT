package com.example.buensaborback.domain.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ImagenEmpleado extends Base {
    private String url;
}
