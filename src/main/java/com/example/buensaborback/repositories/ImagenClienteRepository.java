package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ImagenCliente;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImagenClienteRepository extends ImageRepository<ImagenCliente, UUID> {

}