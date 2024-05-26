package com.example.buensaborback.repositories;


import com.example.buensaborback.domain.entities.Articulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticuloRepository extends BaseRepository<Articulo, Long> {
    @Query("SELECT i FROM Articulo i WHERE i.id = :idArticulo")
    Optional<Articulo> findById(@Param("idArticulo") Long idArticulo);
}
