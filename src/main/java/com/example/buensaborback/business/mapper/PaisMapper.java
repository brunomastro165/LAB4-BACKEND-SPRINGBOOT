package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.Pais.PaisCreateDto;
import com.example.buensaborback.domain.dto.Pais.PaisDto;
import com.example.buensaborback.domain.entities.Pais;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaisMapper extends BaseMapper<Pais, PaisDto, PaisCreateDto, PaisCreateDto> {
}
