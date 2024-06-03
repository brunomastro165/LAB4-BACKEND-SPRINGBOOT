package com.example.buensaborback.domain.dto.Pedido;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioCreateDto;
import com.example.buensaborback.domain.dto.Factura.FacturaDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalDto;
import com.example.buensaborback.domain.enums.Estado;
import com.example.buensaborback.domain.enums.TipoEnvio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto extends BaseDto {
    private Set<DetallePedidoDto> detallesPedido;
    private Double total;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    private DomicilioCreateDto domicilio;

    //private ClienteDto cliente;
    private SucursalDto sucursal;
    //private EmpleadoDto empleado;

    private FacturaDto factura;
}
