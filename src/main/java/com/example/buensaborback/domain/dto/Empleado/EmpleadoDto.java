package com.example.buensaborback.domain.dto.Empleado;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.entities.*;
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


    private UsuarioEmpleado usuario;


    private ImagenEmpleado imagenPersona;


    private Set<Domicilio> domicilios = new HashSet<>();


    private Rol tipoEmpleado;


    private Set<Pedido> pedidos = new HashSet<>();


    private Sucursal sucursal;
}
