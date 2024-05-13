package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dtos.PaisDTO;
import com.example.buensaborback.domain.entities.Pais;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaisMapper extends BaseMapper<Pais, PaisDTO> {
    PaisDTO toDTO(Pais source);

    Pais toEntity(PaisDTO source);

    List<PaisDTO> toDTOsList(List<Pais> source);

    List<Pais> toEntitiesList(List<PaisDTO> source);
}
