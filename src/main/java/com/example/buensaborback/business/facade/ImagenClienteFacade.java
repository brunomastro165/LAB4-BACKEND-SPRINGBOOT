package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteCreateDto;
import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteDto;

public interface ImagenClienteFacade  extends BaseFacade<ImagenClienteDto, ImagenClienteCreateDto, ImagenClienteCreateDto, Long> {
}
