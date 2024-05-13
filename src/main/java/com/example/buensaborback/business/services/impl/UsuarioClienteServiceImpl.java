package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.UsuarioClienteService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.UsuarioCliente;
import com.example.buensaborback.repositories.BaseRepository;
import com.example.buensaborback.repositories.UsuarioClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioClienteServiceImpl extends BaseServiceImpl<UsuarioCliente, Long> implements UsuarioClienteService {
    @Autowired
    private UsuarioClienteRepository usuarioClienteRepository;

    public UsuarioClienteServiceImpl(BaseRepository<UsuarioCliente, Long> baseRepository, UsuarioClienteRepository usuarioClienteRepository) {
        super(baseRepository);
        this.usuarioClienteRepository = usuarioClienteRepository;
    }
}
