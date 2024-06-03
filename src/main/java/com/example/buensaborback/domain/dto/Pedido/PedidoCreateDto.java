package com.example.buensaborback.domain.dto.Pedido;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoCreateDto;
import com.example.buensaborback.domain.dto.Factura.FacturaCreateDto;
import com.example.buensaborback.domain.enums.Estado;
import com.example.buensaborback.domain.enums.TipoEnvio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoCreateDto extends BaseDto {
    private List<DetallePedidoCreateDto> detallesPedido;
    private Double total;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    /*
    private ClienteDto cliente;
    private SucursalDto sucursal;
    private EmpleadoDto empleado;

     */
    private FacturaCreateDto factura;
}
