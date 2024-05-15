package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.services.ProvinciaService;
import com.example.buensaborback.domain.dto.Localidad.LocalidadCreateDto;
import com.example.buensaborback.domain.dto.Localidad.LocalidadDto;
import com.example.buensaborback.domain.entities.Localidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProvinciaService.class})
public interface LocalidadMapper extends BaseMapper<Localidad, LocalidadDto, LocalidadCreateDto, LocalidadCreateDto> {
    @Mapping(target = "provincia", source = "idProvincia", qualifiedByName = "getById")
    Localidad toEntityCreate(LocalidadCreateDto source);
}
