package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.UsuarioClienteFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.UsuarioClienteDTO;
import com.example.buensaborback.domain.entities.UsuarioCliente;
import org.springframework.stereotype.Service;

@Service
public class UsuarioClienteFacadeImpl extends BaseFacadeImpl<UsuarioCliente, UsuarioClienteDTO, Long> implements UsuarioClienteFacade {
    public UsuarioClienteFacadeImpl(BaseService<UsuarioCliente, Long> baseService, BaseMapper<UsuarioCliente, UsuarioClienteDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}