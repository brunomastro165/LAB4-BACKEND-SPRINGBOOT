package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.ImagenArticuloDto.ImagenArticuloCreateDto;
import com.example.buensaborback.domain.dto.ImagenArticuloDto.ImagenArticuloDto;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagenArticuloMapper extends BaseMapper<ImagenArticulo, ImagenArticuloDto, ImagenArticuloCreateDto, ImagenArticuloCreateDto> {
}
