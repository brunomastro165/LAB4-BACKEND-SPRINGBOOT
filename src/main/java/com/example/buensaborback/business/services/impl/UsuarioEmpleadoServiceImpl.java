package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.UsuarioEmpleadoService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.UsuarioEmpleado;
import com.example.buensaborback.repositories.BaseRepository;
import com.example.buensaborback.repositories.UsuarioEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioEmpleadoServiceImpl extends BaseServiceImpl<UsuarioEmpleado, Long> implements UsuarioEmpleadoService {
    @Autowired
    private UsuarioEmpleadoRepository usuarioEmpleadoRepository;

    public UsuarioEmpleadoServiceImpl(BaseRepository<UsuarioEmpleado, Long> baseRepository, UsuarioEmpleadoRepository usuarioEmpleadoRepository) {
        super(baseRepository);
        this.usuarioEmpleadoRepository = usuarioEmpleadoRepository;
    }
}
