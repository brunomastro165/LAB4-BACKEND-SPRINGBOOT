package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dtos.BaseDTO;
import com.example.buensaborback.domain.entities.Base;

import java.util.List;

public interface BaseMapper<E extends Base, D extends BaseDTO> {
    D toDTO(E source);

    E toEntity(D source);

    List<D> toDTOsList(List<E> source);

    List<E> toEntitiesList(List<D> source);
}
