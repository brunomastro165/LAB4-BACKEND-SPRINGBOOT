package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dtos.EmpresaDTO;
import com.example.buensaborback.domain.dtos.shortDTO.EmpresaShortDTO;
import com.example.buensaborback.domain.entities.Empresa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpresaMapper extends BaseMapper<Empresa, EmpresaDTO> {
    EmpresaDTO toDTO(Empresa source);

    Empresa toEntity(EmpresaDTO source);

    List<EmpresaDTO> toDTOsList(List<Empresa> source);

    List<Empresa> toEntitiesList(List<EmpresaDTO> source);

    // Métodos para EmpresaShortDTO
    EmpresaShortDTO toShortDTO(Empresa source);

    Empresa toEntityFromShortDTO(EmpresaShortDTO source);

    List<EmpresaShortDTO> toShortDTOsList(List<Empresa> source);

    List<Empresa> toEntitiesListFromShortDTOs(List<EmpresaShortDTO> source);

}