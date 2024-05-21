package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.services.CategoriaService;
import com.example.buensaborback.business.services.EmpresaService;
import com.example.buensaborback.domain.dto.Sucursal.SucursalCreateDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalEditDto;
import com.example.buensaborback.domain.entities.Sucursal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DomicilioMapper.class, EmpresaService.class} )
public interface SucursalMapper extends BaseMapper<Sucursal, SucursalDto, SucursalCreateDto, SucursalEditDto>{
    //qualifiedByName ="getById" apunta al metodo con @Named de EmpresaService con valor getById
    @Mapping(target = "empresa", source = "idEmpresa", qualifiedByName = "getById")
    public Sucursal toEntityCreate(SucursalCreateDto source);
}
