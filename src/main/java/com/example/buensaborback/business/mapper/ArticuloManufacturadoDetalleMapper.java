package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.services.ArticuloInsumoService;
import com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleEditDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturadoDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {ArticuloInsumoService.class})
public interface ArticuloManufacturadoDetalleMapper extends BaseMapper<ArticuloManufacturadoDetalle, ArticuloManufacturadoDetalleDto,ArticuloManufacturadoDetalleCreateDto, ArticuloManufacturadoDetalleEditDto>{

    ArticuloManufacturadoDetalleMapper INSTANCE = Mappers.getMapper(ArticuloManufacturadoDetalleMapper.class);

    // Utiliza la anotación @Mapping para especificar el mapeo entre los campos del DTO y la entidad,
    // y utiliza el servicio ArticuloInsumoService para obtener el artículo de insumo a partir del ID.

}
