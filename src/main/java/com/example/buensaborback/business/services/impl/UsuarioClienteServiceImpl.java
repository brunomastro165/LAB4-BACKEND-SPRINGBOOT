package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.UsuarioClienteService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.UsuarioCliente;
import com.example.buensaborback.repositories.UsuarioClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioClienteServiceImpl extends BaseServiceImpl<UsuarioCliente, Long> implements UsuarioClienteService {
    @Autowired
    UsuarioClienteRepository usuarioClienteRepository;

    @Override
    public boolean existsByUsername(String nombreUsuario) {
        return usuarioClienteRepository.existsByUserName(nombreUsuario);
    }

}
