package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.services.ClienteService;
import com.example.buensaborback.business.services.DomicilioService;
import com.example.buensaborback.business.services.SucursalService;
import com.example.buensaborback.domain.dto.Pedido.PedidoCreateDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SucursalService.class, DomicilioMapper.class, DetallePedidoMapper.class, FacturaMapper.class, ClienteService.class, DomicilioService.class})
public interface PedidoMapper extends BaseMapper<Pedido, PedidoDto, PedidoCreateDto, PedidoCreateDto> {

    @Mapping(target = "sucursal", source = "idSucursal", qualifiedByName = "getById")
    @Mapping(target = "cliente", source = "idCliente", qualifiedByName = "getById")
    @Mapping(target = "domicilio", source = "idDomicilio", qualifiedByName = "getById")
    Pedido toEntityCreate(PedidoCreateDto source);

}
