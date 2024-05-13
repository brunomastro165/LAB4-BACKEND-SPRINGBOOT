package com.example.buensaborback.domain.dtos;

import com.example.buensaborback.domain.entities.ImagenEmpleado;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.domain.entities.UsuarioEmpleado;
import com.example.buensaborback.domain.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpleadoDTO extends BaseDTO {

    private Rol tipoEmpleado;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;
    private UsuarioEmpleado usuarioEmpleado;
    private ImagenEmpleado imagenEmpleado;
    private Set<Pedido> pedidos = new HashSet<>();
    private Sucursal sucursal;
}
