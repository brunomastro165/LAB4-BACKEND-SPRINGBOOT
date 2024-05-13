package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.DetallePedidoFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.DetallePedidoDTO;
import com.example.buensaborback.domain.entities.DetallePedido;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoFacadeImpl extends BaseFacadeImpl<DetallePedido, DetallePedidoDTO, Long> implements DetallePedidoFacade {
    public DetallePedidoFacadeImpl(BaseService<DetallePedido, Long> baseService, BaseMapper<DetallePedido, DetallePedidoDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}
