package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ImagenClienteFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteCreateDto;
import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteDto;
import com.example.buensaborback.domain.entities.ImagenCliente;
import org.springframework.stereotype.Service;

@Service
public class ImagenClienteFacadeImpl extends BaseFacadeImpl<ImagenCliente, ImagenClienteDto, ImagenClienteCreateDto, ImagenClienteCreateDto, Long> implements ImagenClienteFacade {
    public ImagenClienteFacadeImpl(BaseService<ImagenCliente, Long> baseService, BaseMapper<ImagenCliente, ImagenClienteDto, ImagenClienteCreateDto, ImagenClienteCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
}

