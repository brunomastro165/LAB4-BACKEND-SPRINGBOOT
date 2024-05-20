package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.PromocionFacade;
import com.example.buensaborback.business.facade.UsuarioClienteFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Promocion.PromocionCreateDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import com.example.buensaborback.domain.dto.UsuarioCliente.UsuarioClienteCreateDto;
import com.example.buensaborback.domain.dto.UsuarioCliente.UsuarioClienteDto;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.domain.entities.UsuarioCliente;

public class UsuarioClienteFacadeImpl extends BaseFacadeImpl<UsuarioCliente, UsuarioClienteDto, UsuarioClienteCreateDto, UsuarioClienteCreateDto, Long> implements UsuarioClienteFacade {
    public UsuarioClienteFacadeImpl(BaseService<UsuarioCliente, Long> baseService, BaseMapper<UsuarioCliente, UsuarioClienteDto, UsuarioClienteCreateDto, UsuarioClienteCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
}