package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ClienteFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.ClienteDTO;
import com.example.buensaborback.domain.entities.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteFacadeImpl extends BaseFacadeImpl<Cliente, ClienteDTO, Long> implements ClienteFacade {
    public ClienteFacadeImpl(BaseService<Cliente, Long> baseService, BaseMapper<Cliente, ClienteDTO> baseMapper) {
        super(baseService, baseMapper);
    }
}
