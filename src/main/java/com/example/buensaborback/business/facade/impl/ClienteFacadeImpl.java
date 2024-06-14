package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ClienteFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Cliente.ClienteCreateDto;
import com.example.buensaborback.domain.dto.Cliente.ClienteDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioDto;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.domain.entities.Domicilio;
import org.springframework.stereotype.Service;

@Service
public class ClienteFacadeImpl extends BaseFacadeImpl<Cliente, ClienteDto, ClienteCreateDto, ClienteCreateDto, Long> implements ClienteFacade {
    public ClienteFacadeImpl(BaseService<Cliente, Long> baseService, BaseMapper<Cliente, ClienteDto, ClienteCreateDto, ClienteCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
    public ClienteDto createNew(ClienteCreateDto request) {
        System.out.println("EstoyEnFacade");
        // Convierte a entidad
        var entityToCreate = baseMapper.toEntityCreate(request);
        for (Domicilio domicilio:
        entityToCreate.getDomicilios()) {
            System.out.println(domicilio.getLocalidad());
        }

        // Graba una entidad
        var entityCreated = baseService.create(entityToCreate);
        // convierte a Dto para devolver
        return baseMapper.toDTO(entityCreated);
    }
}
