package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.EmpleadoFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.EmpleadoDTO;
import com.example.buensaborback.domain.entities.Empleado;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoFacadeImpl extends BaseFacadeImpl<Empleado, EmpleadoDTO, Long> implements EmpleadoFacade {
    public EmpleadoFacadeImpl(BaseService<Empleado, Long> baseService, BaseMapper<Empleado, EmpleadoDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}
