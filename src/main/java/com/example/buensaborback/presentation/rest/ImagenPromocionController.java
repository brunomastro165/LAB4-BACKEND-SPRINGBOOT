package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ImagenPromocionFacadeImpl;
import com.example.buensaborback.domain.dto.ImagenPromocion.ImagenPromocionCreateDto;
import com.example.buensaborback.domain.dto.ImagenPromocion.ImagenPromocionDto;
import com.example.buensaborback.domain.entities.ImagenPromocion;
import com.example.buensaborback.presentation.base.BaseControllerImpl;

public class ImagenPromocionController extends BaseControllerImpl<ImagenPromocion, ImagenPromocionDto, ImagenPromocionCreateDto, ImagenPromocionCreateDto, Long, ImagenPromocionFacadeImpl> {
    public ImagenPromocionController(ImagenPromocionFacadeImpl facade) {
        super(facade);
    }
}