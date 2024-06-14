package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.Cliente.ClienteCreateDto;
import com.example.buensaborback.domain.dto.Cliente.ClienteDto;
import com.example.buensaborback.domain.dto.Cliente.ClienteEditDto;

public interface ClienteFacade extends BaseFacade<ClienteDto, ClienteCreateDto, ClienteEditDto, Long> {
}
