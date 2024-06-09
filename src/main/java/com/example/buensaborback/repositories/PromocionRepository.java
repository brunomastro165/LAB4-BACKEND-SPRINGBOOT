package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Promocion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionRepository extends BaseRepository<Promocion, Long> {
    @Query("SELECT p FROM Promocion p LEFT JOIN FETCH p.sucursales WHERE p.id = :id")
    Promocion findAllWithSucursales(@Param("id") Long id);
    @Modifying
    @Query(value = "INSERT INTO sucursal_promocion (sucursal_id, promocion_id) VALUES (:sucursalId, :promocionId)", nativeQuery = true)
    void insertIntoSucursalPromocion(@Param("sucursalId") Long sucursalId, @Param("promocionId") Long promocionId);

    @Modifying
    @Query(value = "DELETE FROM sucursal_promocion WHERE promocion_id = :promocionId", nativeQuery = true)
    void deleteFromSucursalPromocionByPromocionId(@Param("promocionId") Long promocionId);



}
