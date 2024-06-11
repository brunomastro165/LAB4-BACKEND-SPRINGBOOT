package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.Promocion.PromocionCreateDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionEditDto;
import com.example.buensaborback.domain.entities.Promocion;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring", uses = {SucursalService.class, SucursalMapper.class,PromocionDetalleMapper.class})
public interface PromocionMapper extends BaseMapper<Promocion, PromocionDto, PromocionCreateDto, PromocionEditDto> {
    PromocionMapper INSTANCE = Mappers.getMapper(PromocionMapper.class);
/*
    @Mapping(target = "sucursales", source = "idSucursales", qualifiedByName = "getById")
    Promocion toEntityCreate(PromocionCreateDto source);

 */
}
