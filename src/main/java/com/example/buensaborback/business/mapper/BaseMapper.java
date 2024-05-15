package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.entities.Base;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<E extends Base, D extends BaseDto, DC, DE> {
    public D toDTO(E source);

    public E toEntity(D source);

    public E toEntityCreate(DC source);

    //@MappingTarget se utiliza para reemplazar los atributos del dto sobre la entidad
    public E toUpdate(@MappingTarget E entity, DE source);

    public List<D> toDTOsList(List<E> source);
}
