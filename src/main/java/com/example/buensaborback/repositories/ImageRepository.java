package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface ImageRepository<E extends Image, ID extends Serializable> extends JpaRepository<E, ID> {
    void deleteById(UUID id);

    Optional<E> findById(UUID id);

}
