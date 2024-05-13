package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dtos.UsuarioClienteDTO;
import com.example.buensaborback.domain.entities.UsuarioCliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioClienteMapper extends BaseMapper<UsuarioCliente, UsuarioClienteDTO> {
    UsuarioClienteDTO toDTO(UsuarioCliente source);

    UsuarioCliente toEntity(UsuarioClienteDTO source);

    List<UsuarioClienteDTO> toDTOsList(List<UsuarioCliente> source);

    List<UsuarioCliente> toEntitiesList(List<UsuarioClienteDTO> source);
}
