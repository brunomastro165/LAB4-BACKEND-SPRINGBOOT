package com.example.buensaborback.domain.dto.Cliente;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalShortDto;
import com.example.buensaborback.domain.dto.UsuarioCliente.UsuarioClienteDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteShortDto extends BaseDto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private List<SucursalShortDto> sucursales;

}
