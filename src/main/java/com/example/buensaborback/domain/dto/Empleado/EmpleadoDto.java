package com.example.buensaborback.domain.dto.Empleado;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioDto;
import com.example.buensaborback.domain.dto.ImagenEmpleado.ImagenEmpleadoDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalDto;
import com.example.buensaborback.domain.dto.UsuarioEmpleado.UsuarioEmpleadoDto;
import com.example.buensaborback.domain.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDto extends BaseDto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private UsuarioEmpleadoDto usuario;
    private ImagenEmpleadoDto imagenPersona;
    private Set<DomicilioDto> domicilios = new HashSet<>();
    private Rol tipoEmpleado;
    private Set<PedidoDto> pedidos = new HashSet<>();
    private SucursalDto sucursal;
}
