package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.PedidoFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.PedidoDTO;
import com.example.buensaborback.domain.entities.Pedido;
import org.springframework.stereotype.Service;

@Service
public class PedidoFacadeImpl extends BaseFacadeImpl<Pedido, PedidoDTO, Long> implements PedidoFacade {
    public PedidoFacadeImpl(BaseService<Pedido, Long> baseService, BaseMapper<Pedido, PedidoDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}
