package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.services.UnidadMedidaService;
import com.example.buensaborback.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.Insumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UnidadMedidaService.class})
public interface ArticuloInsumoMapper extends BaseMapper<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoCreateDto> {
    @Mapping(target = "unidadMedida", source = "idUnidadMedida", qualifiedByName = "getById")
    ArticuloInsumo toEntityCreate(ArticuloInsumoCreateDto source);
}
