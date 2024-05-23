package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ImagenArticulo;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImagenArticuloRepository extends ImageRepository<ImagenArticulo, UUID> {
}
