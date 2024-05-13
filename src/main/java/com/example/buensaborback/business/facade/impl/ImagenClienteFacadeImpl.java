package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ImagenClienteFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.ImagenClienteDTO;
import com.example.buensaborback.domain.entities.ImagenCliente;
import org.springframework.stereotype.Service;

@Service
public class ImagenClienteFacadeImpl extends BaseFacadeImpl<ImagenCliente, ImagenClienteDTO, Long> implements ImagenClienteFacade {
    public ImagenClienteFacadeImpl(BaseService<ImagenCliente, Long> baseService, BaseMapper<ImagenCliente, ImagenClienteDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}
