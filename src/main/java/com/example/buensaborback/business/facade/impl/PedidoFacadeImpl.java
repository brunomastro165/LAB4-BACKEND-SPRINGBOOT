package com.example.buensaborback.business.facade.impl;


import com.example.buensaborback.business.facade.PedidoFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.PedidoService;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Pedido.PedidoCreateDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoFacadeImpl extends BaseFacadeImpl<Pedido, PedidoDto, PedidoCreateDto, PedidoCreateDto, Long> implements PedidoFacade {
    @Autowired
    PedidoService pedidoService;

    public PedidoFacadeImpl(BaseService<Pedido, Long> baseService, BaseMapper<Pedido, PedidoDto, PedidoCreateDto, PedidoCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }

    public PedidoDto asignarEmpleado(Long idEmpleado, Long idPedido) {
        return baseMapper.toDTO(pedidoService.asignarEmpleado(idEmpleado, idPedido));
    }
    public PedidoDto cancelar(Long id){
        return baseMapper.toDTO(pedidoService.cancelar(id));
    }
    public PedidoDto pendiente(Long id){
        return baseMapper.toDTO(pedidoService.pendiente(id));
    }
}
