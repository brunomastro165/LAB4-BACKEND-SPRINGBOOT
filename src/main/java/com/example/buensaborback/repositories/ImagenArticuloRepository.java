package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ImagenArticulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImagenArticuloRepository extends ImageRepository<ImagenArticulo, UUID> {
    @Query("SELECT i FROM ImagenArticulo i WHERE i.articulo.id = :idArticulo")
    Optional<ImagenArticulo> findByIdArticulo(@Param("idArticulo") Long idArticulo);
}
