package com.example.buensaborback.business.facade.impl;


import com.example.buensaborback.business.facade.ImagenArticuloFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.ImagenArticuloDto.ImagenArticuloCreateDto;
import com.example.buensaborback.domain.dto.ImagenArticuloDto.ImagenArticuloDto;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import org.springframework.stereotype.Service;

@Service
public class ImagenArticuloFacadeImpl extends BaseFacadeImpl<ImagenArticulo, ImagenArticuloDto, ImagenArticuloCreateDto, ImagenArticuloCreateDto, Long> implements ImagenArticuloFacade {
    public ImagenArticuloFacadeImpl(BaseService<ImagenArticulo, Long> baseService, BaseMapper<ImagenArticulo, ImagenArticuloDto, ImagenArticuloCreateDto, ImagenArticuloCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
}

