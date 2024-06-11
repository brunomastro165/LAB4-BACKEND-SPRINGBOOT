package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.services.CategoriaService;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoEditDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CategoriaService.class})
public interface ArticuloInsumoMapper extends BaseMapper<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoEditDto> {

    ArticuloInsumoMapper INSTANCE = Mappers.getMapper(ArticuloInsumoMapper.class);

    @Mapping(target = "categoria", source = "idCategoria", qualifiedByName = "getById")
    ArticuloInsumo toEntityCreate(ArticuloInsumoCreateDto source);

    @Mapping(target = "categoria", source = "idCategoria", qualifiedByName = "getById")
    ArticuloInsumo toUpdate(ArticuloInsumoEditDto source);

}
