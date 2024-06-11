package com.example.buensaborback.domain.dto.Cliente;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioDto;
import com.example.buensaborback.domain.dto.UsuarioCliente.UsuarioClienteCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCreateDto extends BaseDto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private UsuarioClienteCreateDto usuario;
    private Set<DomicilioDto> domicilios;
}
