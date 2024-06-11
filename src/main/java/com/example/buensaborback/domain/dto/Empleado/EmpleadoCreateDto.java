package com.example.buensaborback.domain.dto.Empleado;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioCreateDto;
import com.example.buensaborback.domain.dto.UsuarioEmpleado.UsuarioEmpleadoCreateDto;
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
public class EmpleadoCreateDto extends BaseDto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private UsuarioEmpleadoCreateDto usuario;
    private Set<DomicilioCreateDto> domicilios = new HashSet<>();
    private Rol tipoEmpleado;
    private Long idSucursal;
}
