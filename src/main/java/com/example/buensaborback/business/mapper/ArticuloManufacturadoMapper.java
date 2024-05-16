package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.services.ArticuloManufacturadoDetalleService;
import com.example.buensaborback.business.services.UnidadMedidaService;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ArticuloManufacturadoDetalleService.class, UnidadMedidaService.class})
public interface ArticuloManufacturadoMapper extends BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoCreateDto> {


    public ArticuloManufacturado toEntityCreate(ArticuloManufacturadoCreateDto source);
}
