package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.SucursalService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.repositories.BaseRepository;
import com.example.buensaborback.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalServiceImpl extends BaseServiceImpl<Sucursal, Long> implements SucursalService {
    @Autowired
    private SucursalRepository sucursalRepository;

    public SucursalServiceImpl(BaseRepository<Sucursal, Long> baseRepository, SucursalRepository sucursalRepository) {
        super(baseRepository);
        this.sucursalRepository = sucursalRepository;
    }
}
