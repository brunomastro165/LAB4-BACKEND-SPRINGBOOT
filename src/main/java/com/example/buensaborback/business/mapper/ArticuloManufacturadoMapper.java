package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.services.ArticuloManufacturadoDetalleService;
import com.example.buensaborback.business.services.CategoriaService;
import com.example.buensaborback.business.services.UnidadMedidaService;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoEditDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ArticuloManufacturadoDetalleMapper.class, UnidadMedidaService.class, ImagenArticuloMapper.class})
public interface ArticuloManufacturadoMapper extends BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoEditDto> {

    // Esta es una instancia estática de la interfaz, que se utiliza para obtener una instancia del Mapper.
    ArticuloManufacturadoMapper INSTANCE = Mappers.getMapper(ArticuloManufacturadoMapper.class);

    // Este método define la transformación de un ArticuloManufacturadoCreateDto a una entidad ArticuloManufacturado.
    // Utiliza la anotación @Mappings para especificar múltiples mapeos entre los campos del DTO y la entidad.
    @Mappings({
            @Mapping(target = "articuloManufacturadoDetalles", qualifiedByName = "toEntityCreateSetDetalle"),
            //usamos toEntityCreateSetDetalle para utilizar el mapper de ArticuloManufacturadoDetalle para convertir los detalles a entidad
            @Mapping(target = "unidadMedida", source = "idUnidadMedida",qualifiedByName = "getById"),
            //consumimos el getById para recuperar la unidad de medida de la base de datos
            @Mapping(target = "eliminado", constant = "true")
            //se utiliza constant ="true" porque mapstruct para los atributos booleanos asigna false por defecto
    })
    // Este método define la transformación de un ArticuloManufacturadoCreateDto a una entidad ArticuloManufacturado.
    public ArticuloManufacturado toEntityCreate(ArticuloManufacturadoCreateDto source);

    @Mapping(target = "unidadMedida", source = "idUnidadMedida",qualifiedByName = "getById")
    public ArticuloManufacturado toUpdate(@MappingTarget ArticuloManufacturado entity, ArticuloManufacturadoEditDto source);

}

