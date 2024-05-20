package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.UsuarioClienteFacadeImpl;
import com.example.buensaborback.domain.dto.UsuarioCliente.UsuarioClienteCreateDto;
import com.example.buensaborback.domain.dto.UsuarioCliente.UsuarioClienteDto;
import com.example.buensaborback.domain.entities.UsuarioCliente;
import com.example.buensaborback.presentation.base.BaseControllerImpl;

public class UsuarioClienteController extends BaseControllerImpl<UsuarioCliente, UsuarioClienteDto, UsuarioClienteCreateDto, UsuarioClienteCreateDto, Long, UsuarioClienteFacadeImpl> {
    public UsuarioClienteController(UsuarioClienteFacadeImpl facade) {
        super(facade);
    }
}
