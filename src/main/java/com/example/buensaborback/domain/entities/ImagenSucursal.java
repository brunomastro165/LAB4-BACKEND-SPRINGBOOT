package com.example.buensaborback.domain.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
public class ImagenSucursal extends Image {
}
