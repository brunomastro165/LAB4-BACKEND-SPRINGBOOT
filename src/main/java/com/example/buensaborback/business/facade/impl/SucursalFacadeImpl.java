package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.Sucursalfacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Sucursal.SucursalCreateDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalEditDto;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.stereotype.Service;

@Service
public class SucursalFacadeImpl extends BaseFacadeImpl<Sucursal, SucursalDto, SucursalCreateDto, SucursalEditDto, Long> implements Sucursalfacade {
    public SucursalFacadeImpl(BaseService<Sucursal, Long> baseService, BaseMapper<Sucursal, SucursalDto, SucursalCreateDto, SucursalEditDto> baseMapper) {
        super(baseService, baseMapper);
    }


}
