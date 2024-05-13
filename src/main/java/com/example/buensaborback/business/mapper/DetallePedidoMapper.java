package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dtos.DetallePedidoDTO;
import com.example.buensaborback.domain.entities.DetallePedido;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetallePedidoMapper extends BaseMapper<DetallePedido, DetallePedidoDTO> {
    DetallePedidoDTO toDTO(DetallePedido source);

    DetallePedido toEntity(DetallePedidoDTO source);

    List<DetallePedidoDTO> toDTOsList(List<DetallePedido> source);

    List<DetallePedido> toEntitiesList(List<DetallePedidoDTO> source);
}
