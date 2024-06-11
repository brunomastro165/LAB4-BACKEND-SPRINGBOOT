package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends BaseRepository<Empleado, Long> {
    @Query("SELECT e FROM Empleado e WHERE e.sucursal.id = ?1")
    List<Empleado> findEmpleadosBySucursalId(Long sucursalId);

}
