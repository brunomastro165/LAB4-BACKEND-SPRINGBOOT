package com.example.buensaborback.repositories;

import com.example.buensaborback.entities.ArticuloInsumo;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Repository
public interface ArticuloInsumoRepository extends ArticuloRepository<ArticuloInsumo,Long> {
}
