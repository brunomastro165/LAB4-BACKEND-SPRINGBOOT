package com.example.buensaborback.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ImagenArticulo extends Base{

    private String url;

}
