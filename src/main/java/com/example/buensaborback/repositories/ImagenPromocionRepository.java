package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ImagenPromocion;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImagenPromocionRepository extends ImageRepository<ImagenPromocion, UUID> {
}
