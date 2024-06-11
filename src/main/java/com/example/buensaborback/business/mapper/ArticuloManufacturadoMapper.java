package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.services.CategoriaService;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoEditDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CategoriaService.class})
public interface ArticuloManufacturadoMapper extends BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoEditDto> {

    // Esta es una instancia estática de la interfaz, que se utiliza para obtener una instancia del Mapper.
    ArticuloManufacturadoMapper INSTANCE = Mappers.getMapper(ArticuloManufacturadoMapper.class);

    @Mapping(target = "categoria", source = "idCategoria", qualifiedByName = "getById")
    ArticuloManufacturado toEntityCreate(ArticuloManufacturadoCreateDto source);

    @Mapping(target = "categoria", source = "idCategoria", qualifiedByName = "getById")
    ArticuloManufacturado toUpdate(ArticuloManufacturadoEditDto source);


}

