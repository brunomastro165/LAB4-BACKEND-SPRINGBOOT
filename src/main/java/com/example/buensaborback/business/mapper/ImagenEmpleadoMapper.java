package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dtos.ImagenEmpleadoDTO;
import com.example.buensaborback.domain.entities.ImagenEmpleado;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImagenEmpleadoMapper extends BaseMapper<ImagenEmpleado, ImagenEmpleadoDTO> {
    ImagenEmpleadoDTO toDTO(ImagenEmpleado source);

    ImagenEmpleado toEntity(ImagenEmpleadoDTO source);

    List<ImagenEmpleadoDTO> toDTOsList(List<ImagenEmpleado> source);

    List<ImagenEmpleado> toEntitiesList(List<ImagenEmpleadoDTO> source);
}
