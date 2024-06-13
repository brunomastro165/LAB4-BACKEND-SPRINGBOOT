package com.example.buensaborback.business.services;

import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Pedido.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface PedidoService extends BaseService<Pedido, Long> {
    List<PedidoDto> getPorFecha(Date fechaInicio,Date fechaFin, Long idSucursal);
    Pedido asignarEmpleado(Long idEmpleado,Long idPedido);
}
