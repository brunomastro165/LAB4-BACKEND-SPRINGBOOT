package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoCreateDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoDto;
import com.example.buensaborback.domain.entities.DetallePedido;

//@Mapper(componentModel = "spring",uses = {PromocionService.class})
public interface DetallePedidoMapper extends BaseMapper<DetallePedido, DetallePedidoDto, DetallePedidoCreateDto, DetallePedidoCreateDto> {
/*
    @Mapping(target = "promocion", source = "idPromocion", qualifiedByName = "getById")
    DetallePedido toEntityCreate(DetallePedidoCreateDto source);
*/

}