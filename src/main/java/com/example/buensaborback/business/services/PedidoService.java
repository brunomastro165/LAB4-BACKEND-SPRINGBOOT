package com.example.buensaborback.business.services;

import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Pedido.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;

import java.util.Date;
import java.util.List;

public interface PedidoService extends BaseService<Pedido, Long> {
    List<PedidoDto> getPorFecha(Date fechaInicio, Date fechaFin, Long idSucursal);

    Pedido asignarEmpleado(Long idEmpleado, Long idPedido);
    Pedido cancelar(Long id);
    Pedido pendiente(Long id);
}
