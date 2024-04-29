package com.example.buensaborback.repositories;

import com.example.buensaborback.entities.Articulo;
import com.example.buensaborback.entities.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface ArticuloRepository <E extends Articulo, ID extends Serializable> extends JpaRepository<E, ID> {
}
