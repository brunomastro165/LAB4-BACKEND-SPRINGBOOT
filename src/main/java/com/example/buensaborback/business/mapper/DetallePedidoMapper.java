package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.services.PromocionService;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoCreateDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoCreateDto;
import com.example.buensaborback.domain.entities.DetallePedido;
import com.example.buensaborback.domain.entities.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {PromocionService.class})
public interface DetallePedidoMapper extends BaseMapper<DetallePedido, DetallePedidoDto, DetallePedidoCreateDto, DetallePedidoCreateDto> {

    @Mapping(target = "promocion", source = "idPromocion", qualifiedByName = "getById")
    DetallePedido toEntityCreate(DetallePedidoCreateDto source);


}