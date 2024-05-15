package com.example.buensaborback.business.mapper;


import com.example.buensaborback.domain.dto.Empresa.EmpresaCreateDto;
import com.example.buensaborback.domain.dto.Empresa.EmpresaDto;
import com.example.buensaborback.domain.dto.Empresa.EmpresaLargeDto;
import com.example.buensaborback.domain.entities.Empresa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpresaMapper extends BaseMapper<Empresa, EmpresaDto, EmpresaCreateDto, EmpresaCreateDto> {
    EmpresaLargeDto toLargeDto(Empresa empresa);
}
