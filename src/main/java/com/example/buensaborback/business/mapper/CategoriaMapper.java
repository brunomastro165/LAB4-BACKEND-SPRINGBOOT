package com.example.buensaborback.business.mapper;


import com.example.buensaborback.domain.dtos.CategoriaDTO;
import com.example.buensaborback.domain.entities.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaDTO> {
    CategoriaDTO toDTO(Categoria source);

    Categoria toEntity(CategoriaDTO source);

    List<CategoriaDTO> toDTOsList(List<Categoria> source);

    List<Categoria> toEntitiesList(List<CategoriaDTO> source);
}
