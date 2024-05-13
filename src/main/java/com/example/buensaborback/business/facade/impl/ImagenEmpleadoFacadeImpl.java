package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ImagenEmpleadoFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.ImagenEmpleadoDTO;
import com.example.buensaborback.domain.entities.ImagenEmpleado;
import org.springframework.stereotype.Service;

@Service
public class ImagenEmpleadoFacadeImpl extends BaseFacadeImpl<ImagenEmpleado, ImagenEmpleadoDTO, Long> implements ImagenEmpleadoFacade {
    public ImagenEmpleadoFacadeImpl(BaseService<ImagenEmpleado, Long> baseService, BaseMapper<ImagenEmpleado, ImagenEmpleadoDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}