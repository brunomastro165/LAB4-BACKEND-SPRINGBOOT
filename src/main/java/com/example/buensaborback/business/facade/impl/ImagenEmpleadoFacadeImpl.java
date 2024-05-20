package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ImagenClienteFacade;
import com.example.buensaborback.business.facade.ImagenEmpleadoFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteCreateDto;
import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteDto;
import com.example.buensaborback.domain.dto.ImagenEmpleado.ImagenEmpleadoCreateDto;
import com.example.buensaborback.domain.dto.ImagenEmpleado.ImagenEmpleadoDto;
import com.example.buensaborback.domain.entities.ImagenCliente;
import com.example.buensaborback.domain.entities.ImagenEmpleado;

public class ImagenEmpleadoFacadeImpl extends BaseFacadeImpl<ImagenEmpleado, ImagenEmpleadoDto, ImagenEmpleadoCreateDto, ImagenEmpleadoCreateDto, Long> implements ImagenEmpleadoFacade {
    public ImagenEmpleadoFacadeImpl(BaseService<ImagenEmpleado, Long> baseService, BaseMapper<ImagenEmpleado,ImagenEmpleadoDto, ImagenEmpleadoCreateDto, ImagenEmpleadoCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
