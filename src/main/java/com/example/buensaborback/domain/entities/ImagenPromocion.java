package com.example.buensaborback.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
public class ImagenPromocion extends Image {
    @ManyToOne
    private Promocion promocion;
}
