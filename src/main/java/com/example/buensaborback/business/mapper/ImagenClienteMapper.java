package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dtos.ImagenClienteDTO;
import com.example.buensaborback.domain.entities.ImagenCliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImagenClienteMapper extends BaseMapper<ImagenCliente, ImagenClienteDTO> {
    ImagenClienteDTO toDTO(ImagenCliente source);

    ImagenCliente toEntity(ImagenClienteDTO source);

    List<ImagenClienteDTO> toDTOsList(List<ImagenCliente> source);

    List<ImagenCliente> toEntitiesList(List<ImagenClienteDTO> source);
}
