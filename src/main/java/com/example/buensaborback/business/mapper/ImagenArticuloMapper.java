package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dtos.ImagenArticuloDTO;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImagenArticuloMapper extends BaseMapper<ImagenArticulo, ImagenArticuloDTO> {
    ImagenArticuloDTO toDTO(ImagenArticulo source);

    ImagenArticulo toEntity(ImagenArticuloDTO source);

    List<ImagenArticuloDTO> toDTOsList(List<ImagenArticulo> source);

    List<ImagenArticulo> toEntitiesList(List<ImagenArticuloDTO> source);
}

