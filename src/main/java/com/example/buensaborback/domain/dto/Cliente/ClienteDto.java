package com.example.buensaborback.domain.dto.Cliente;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioDto;
import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoDto;
import com.example.buensaborback.domain.dto.UsuarioCliente.UsuarioClienteDto;
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
public class ClienteDto extends BaseDto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    private UsuarioClienteDto usuario;


    private ImagenClienteDto imagenCliente;


    private Set<DomicilioDto> domicilios = new HashSet<>();


    private Set<PedidoDto> pedidos = new HashSet<>();
}
