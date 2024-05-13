package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dtos.ArticuloInsumoDTO;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticuloInsumoMapper extends BaseMapper<ArticuloInsumo, ArticuloInsumoDTO> {
    ArticuloInsumoDTO toDTO(ArticuloInsumo source);

    ArticuloInsumo toEntity(ArticuloInsumoDTO source);

    List<ArticuloInsumoDTO> toDTOsList(List<ArticuloInsumo> source);

    List<ArticuloInsumo> toEntitiesList(List<ArticuloInsumoDTO> source);
}
