package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.ImagenEmpleado.ImagenEmpleadoCreateDto;
import com.example.buensaborback.domain.dto.ImagenEmpleado.ImagenEmpleadoDto;
import com.example.buensaborback.domain.entities.ImagenEmpleado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagenEmpleadoMapper extends BaseMapper<ImagenEmpleado, ImagenEmpleadoDto, ImagenEmpleadoCreateDto, ImagenEmpleadoCreateDto> {
}
