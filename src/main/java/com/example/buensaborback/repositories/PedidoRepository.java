package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.time.LocalDate;
import org.springframework.data.repository.query.Param;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {
    @Query("select sum(p.total) from Pedido p where p.fechaPedido >= :fechaInicio")
    Optional<Double> getIngresos(@Param("fechaInicio") LocalDate fechaInicio);
}


