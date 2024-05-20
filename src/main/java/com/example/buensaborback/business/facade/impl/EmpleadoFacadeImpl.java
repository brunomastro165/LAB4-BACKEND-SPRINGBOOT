package com.example.buensaborback.business.facade.impl;


import com.example.buensaborback.business.facade.EmpleadoFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoCreateDto;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoDto;
import com.example.buensaborback.domain.entities.Empleado;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoFacadeImpl extends BaseFacadeImpl<Empleado, EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto, Long> implements EmpleadoFacade {
    public EmpleadoFacadeImpl(BaseService<Empleado, Long> baseService, BaseMapper<Empleado, EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
