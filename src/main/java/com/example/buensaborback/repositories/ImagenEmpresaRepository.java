package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ImagenEmpresa;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImagenEmpresaRepository extends ImageRepository<ImagenEmpresa, UUID> {
}
