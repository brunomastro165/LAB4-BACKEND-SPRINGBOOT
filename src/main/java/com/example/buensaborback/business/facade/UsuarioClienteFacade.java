package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.UsuarioCliente.UsuarioClienteCreateDto;
import com.example.buensaborback.domain.dto.UsuarioCliente.UsuarioClienteDto;

public interface UsuarioClienteFacade extends BaseFacade<UsuarioClienteDto, UsuarioClienteCreateDto, UsuarioClienteCreateDto, Long> {
}
