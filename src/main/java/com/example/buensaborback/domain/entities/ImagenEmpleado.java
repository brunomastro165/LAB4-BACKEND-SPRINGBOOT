package com.example.buensaborback.domain.entities;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ImagenEmpleado extends Image {
}
