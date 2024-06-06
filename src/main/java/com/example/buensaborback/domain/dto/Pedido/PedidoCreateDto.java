package com.example.buensaborback.domain.dto.Pedido;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Cliente.ClienteDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoCreateDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioCreateDto;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoDto;
import com.example.buensaborback.domain.dto.Factura.FacturaCreateDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalDto;
import com.example.buensaborback.domain.enums.Estado;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.domain.enums.TipoEnvio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoCreateDto extends BaseDto {
    private List<DetallePedidoCreateDto> detallesPedido;
    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    private FormaPago formaPago;
    private LocalDate fechaPedido;
    private DomicilioCreateDto domicilio;

    private Long idCliente;
    private Long idSucursal;
    private EmpleadoDto empleado;


    private FacturaCreateDto factura;
}
