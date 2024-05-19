package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.entities.Base;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<E extends Base, D extends BaseDto, DC, DE> {
    D toDTO(E source);

    E toEntity(D source);

    E toEntityCreate(DC source);

    //@MappingTarget se utiliza para reemplazar los atributos del dto sobre la entidad
    E toUpdate(@MappingTarget E entity, DE source);

    List<D> toDTOsList(List<E> source);
}
