package com.example.buensaborback.repositories;

import com.example.buensaborback.entities.ArticuloManufacturado;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Repository
public interface ArticuloManufacturadoRepository extends ArticuloRepository<ArticuloManufacturado,Long> {
}
