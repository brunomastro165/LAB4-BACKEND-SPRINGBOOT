package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoCreateDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoDto;

public interface DetallePedidoFacade extends BaseFacade<DetallePedidoDto, DetallePedidoCreateDto, DetallePedidoCreateDto, Long> {
}
