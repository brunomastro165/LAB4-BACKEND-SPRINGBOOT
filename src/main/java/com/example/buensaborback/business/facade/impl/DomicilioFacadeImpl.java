package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.DomicilioFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.ClienteService;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioCreateDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioDto;
import com.example.buensaborback.domain.entities.Domicilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomicilioFacadeImpl extends BaseFacadeImpl<Domicilio, DomicilioDto, DomicilioCreateDto, DomicilioCreateDto, Long> implements DomicilioFacade {
    @Autowired
    private ClienteService clienteService;
    public DomicilioFacadeImpl(BaseService<Domicilio, Long> baseService, BaseMapper<Domicilio, DomicilioDto, DomicilioCreateDto, DomicilioCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
    public DomicilioDto addDomiciliaACliente(DomicilioCreateDto domicilioCreateDto,Long idCliente){
        Domicilio domicilio = baseMapper.toEntityCreate(domicilioCreateDto);
        domicilio.getClientes().add(clienteService.getById(idCliente));
        domicilio = baseService.create(domicilio);
        return baseMapper.toDTO(domicilio);
    }
}
