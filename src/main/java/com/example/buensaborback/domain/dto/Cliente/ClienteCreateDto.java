package com.example.buensaborback.domain.dto.Cliente;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.UsuarioCliente.UsuarioClienteCreateDto;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.entities.UsuarioCliente;
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
public class ClienteCreateDto extends BaseDto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    private UsuarioClienteCreateDto usuario;


    private Set<Domicilio> domicilios = new HashSet<>();


    private Set<Pedido> pedidos = new HashSet<>();
}
