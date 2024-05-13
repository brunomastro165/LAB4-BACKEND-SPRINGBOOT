package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dtos.PedidoDTO;
import com.example.buensaborback.domain.entities.Pedido;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper extends BaseMapper<Pedido, PedidoDTO> {
    PedidoDTO toDTO(Pedido source);

    Pedido toEntity(PedidoDTO source);

    List<PedidoDTO> toDTOsList(List<Pedido> source);

    List<Pedido> toEntitiesList(List<PedidoDTO> source);
}
