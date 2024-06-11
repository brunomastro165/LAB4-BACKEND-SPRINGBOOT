package com.example.buensaborback.domain.entities;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@AllArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
public class ImagenCliente extends Image {
}
