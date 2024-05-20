package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.DetallePedidoFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoCreateDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoDto;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoCreateDto;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoDto;
import com.example.buensaborback.domain.entities.DetallePedido;
import com.example.buensaborback.domain.entities.Empleado;

public class EmpleadoFacadeImpl extends BaseFacadeImpl<Empleado, EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto, Long> implements DetallePedidoFacade {
    public DetallePedidoFacadeImpl(BaseService<DetallePedido, Long> baseService, BaseMapper<DetallePedido, DetallePedidoDto, DetallePedidoCreateDto, DetallePedidoCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
