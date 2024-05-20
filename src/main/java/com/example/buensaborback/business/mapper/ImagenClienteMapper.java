package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteCreateDto;
import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteDto;
import com.example.buensaborback.domain.entities.ImagenCliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagenClienteMapper extends BaseMapper<ImagenCliente, ImagenClienteDto, ImagenClienteCreateDto, ImagenClienteCreateDto> {
}