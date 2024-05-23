package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ImagenEmpleado;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImagenEmpleadoRepository extends ImageRepository<ImagenEmpleado, UUID> {
}
