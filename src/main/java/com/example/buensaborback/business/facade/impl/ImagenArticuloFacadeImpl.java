package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ImagenArticuloFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.ImagenArticuloDTO;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import org.springframework.stereotype.Service;

@Service
public class ImagenArticuloFacadeImpl extends BaseFacadeImpl<ImagenArticulo, ImagenArticuloDTO, Long> implements ImagenArticuloFacade {
    public ImagenArticuloFacadeImpl(BaseService<ImagenArticulo, Long> baseService, BaseMapper<ImagenArticulo, ImagenArticuloDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}
