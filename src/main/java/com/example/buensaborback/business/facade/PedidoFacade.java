package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.Pedido.PedidoCreateDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoDto;

public interface PedidoFacade extends BaseFacade<PedidoDto, PedidoCreateDto, PedidoCreateDto, Long> {
}
