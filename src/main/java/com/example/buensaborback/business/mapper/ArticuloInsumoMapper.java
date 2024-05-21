package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.services.CategoriaService;
import com.example.buensaborback.business.services.UnidadMedidaService;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoEditDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UnidadMedidaService.class, UnidadMedidaMapper.class, ImagenArticuloMapper.class})
public interface ArticuloInsumoMapper extends BaseMapper<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoEditDto> {

    // Esta es una instancia estática de la interfaz, que se utiliza para obtener una instancia del Mapper.
    ArticuloInsumoMapper INSTANCE = Mappers.getMapper(ArticuloInsumoMapper.class);
    // Utiliza la anotación @Mapping para especificar el mapeo entre los campos del DTO y la entidad,
    // y utiliza el servicio UnidadMedidaService para obtener la unidad de medida a partir del ID.
    @Mapping(target = "unidadMedida", source = "idUnidadMedida",qualifiedByName = "getById")
    @Mapping(target = "eliminado", constant = "true")
    ArticuloInsumo toEntityCreate(ArticuloInsumoCreateDto source);

    @Mapping(target = "unidadMedida", source = "idUnidadMedida", qualifiedByName = "getById")
    ArticuloInsumo toUpdate(@MappingTarget ArticuloInsumo entity, ArticuloInsumoEditDto source);


}
