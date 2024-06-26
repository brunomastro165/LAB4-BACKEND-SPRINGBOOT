package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.UsuarioEmpleadoFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.UsuarioEmpleado.UsuarioEmpleadoCreateDto;
import com.example.buensaborback.domain.dto.UsuarioEmpleado.UsuarioEmpleadoDto;
import com.example.buensaborback.domain.entities.UsuarioEmpleado;
import org.springframework.stereotype.Service;

@Service
public class UsuarioEmpleadoFacadeImpl extends BaseFacadeImpl<UsuarioEmpleado, UsuarioEmpleadoDto, UsuarioEmpleadoCreateDto, UsuarioEmpleadoCreateDto, Long> implements UsuarioEmpleadoFacade {
    public UsuarioEmpleadoFacadeImpl(BaseService<UsuarioEmpleado, Long> baseService, BaseMapper<UsuarioEmpleado, UsuarioEmpleadoDto, UsuarioEmpleadoCreateDto, UsuarioEmpleadoCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
}