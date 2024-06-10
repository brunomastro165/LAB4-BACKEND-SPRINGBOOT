package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.facade.ArticuloInsumoFacade;
import com.example.buensaborback.business.facade.ArticuloManufacturadoFacade;
import com.example.buensaborback.business.services.ArticuloInsumoService;
import com.example.buensaborback.business.services.ArticuloManufacturadoService;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleCreateDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface PromocionDetalleMapper extends BaseMapper<PromocionDetalle, PromocionDetalleDto, PromocionDetalleCreateDto, PromocionDetalleCreateDto> {

}
