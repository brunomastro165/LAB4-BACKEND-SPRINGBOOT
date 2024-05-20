package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ImagenPromocionFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.ImagenPromocion.ImagenPromocionCreateDto;
import com.example.buensaborback.domain.dto.ImagenPromocion.ImagenPromocionDto;
import com.example.buensaborback.domain.entities.ImagenPromocion;
import org.springframework.stereotype.Service;

@Service
public class ImagenPromocionFacadeImpl extends BaseFacadeImpl<ImagenPromocion, ImagenPromocionDto, ImagenPromocionCreateDto, ImagenPromocionCreateDto, Long> implements ImagenPromocionFacade {
    public ImagenPromocionFacadeImpl(BaseService<ImagenPromocion, Long> baseService, BaseMapper<ImagenPromocion, ImagenPromocionDto, ImagenPromocionCreateDto, ImagenPromocionCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
