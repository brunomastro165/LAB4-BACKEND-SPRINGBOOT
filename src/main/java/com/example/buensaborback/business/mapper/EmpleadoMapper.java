package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.Empleado.EmpleadoCreateDto;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoDto;
import com.example.buensaborback.domain.entities.Empleado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper extends BaseMapper<Empleado, EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto> {
}
