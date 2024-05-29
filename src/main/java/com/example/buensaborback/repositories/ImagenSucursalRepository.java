package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ImagenSucursal;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImagenSucursalRepository extends ImageRepository<ImagenSucursal, UUID> {
}
