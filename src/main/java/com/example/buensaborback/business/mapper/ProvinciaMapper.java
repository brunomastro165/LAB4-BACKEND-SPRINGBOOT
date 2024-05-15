package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.services.PaisService;
import com.example.buensaborback.domain.dto.Provincia.ProvinciaCreateDto;
import com.example.buensaborback.domain.dto.Provincia.ProvinciaDto;
import com.example.buensaborback.domain.entities.Provincia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PaisService.class})
public interface ProvinciaMapper extends BaseMapper<Provincia, ProvinciaDto, ProvinciaCreateDto, ProvinciaCreateDto> {
    @Mapping(target = "pais", source = "idPais", qualifiedByName = "getById")
    Provincia toEntityCreate(ProvinciaCreateDto source);
}
