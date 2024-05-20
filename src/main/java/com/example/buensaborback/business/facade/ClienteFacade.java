package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.Cliente.ClienteCreateDto;
import com.example.buensaborback.domain.dto.Cliente.ClienteDto;

public interface ClienteFacade extends BaseFacade<ClienteDto, ClienteCreateDto, ClienteCreateDto, Long> {
}
