package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.DetallePedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import org.springframework.data.repository.query.Param;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido, Long> {

}


