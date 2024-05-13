package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dtos.LocalidadDTO;
import com.example.buensaborback.domain.entities.Localidad;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocalidadMapper extends BaseMapper<Localidad, LocalidadDTO> {
    LocalidadDTO toDTO(Localidad source);

    Localidad toEntity(LocalidadDTO source);

    List<LocalidadDTO> toDTOsList(List<Localidad> source);

    List<Localidad> toEntitiesList(List<LocalidadDTO> source);
}
