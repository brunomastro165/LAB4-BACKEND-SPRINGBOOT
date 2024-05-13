package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ImagenPromocionFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.ImagenPromocionDTO;
import com.example.buensaborback.domain.entities.ImagenPromocion;
import org.springframework.stereotype.Service;

@Service
public class ImagenPromocionFacadeImpl extends BaseFacadeImpl<ImagenPromocion, ImagenPromocionDTO, Long> implements ImagenPromocionFacade {
    public ImagenPromocionFacadeImpl(BaseService<ImagenPromocion, Long> baseService, BaseMapper<ImagenPromocion, ImagenPromocionDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}
