package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ImagenClienteFacadeImpl;
import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteCreateDto;
import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteDto;
import com.example.buensaborback.domain.entities.ImagenCliente;
import com.example.buensaborback.presentation.base.BaseControllerImpl;

public class ImagenClienteController extends BaseControllerImpl<ImagenCliente, ImagenClienteDto, ImagenClienteCreateDto, ImagenClienteCreateDto, Long, ImagenClienteFacadeImpl> {
    public ImagenClienteController(ImagenClienteFacadeImpl facade) {
        super(facade);
    }
}
