package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.services.SucursalService;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoCreateDto;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoDto;
import com.example.buensaborback.domain.entities.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SucursalService.class,PedidoMapper.class})
public interface EmpleadoMapper extends BaseMapper<Empleado, EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto> {
    @Mapping(target = "sucursal", source = "idSucursal", qualifiedByName = "getById")
    Empleado toEntityCreate(EmpleadoCreateDto source);

    @Mapping(target = "sucursal", source = "idSucursal", qualifiedByName = "getById")
    Empleado toUpdate(EmpleadoCreateDto source);
}
