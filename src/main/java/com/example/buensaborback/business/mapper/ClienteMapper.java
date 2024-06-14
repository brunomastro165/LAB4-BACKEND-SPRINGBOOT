package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.Cliente.ClienteCreateDto;
import com.example.buensaborback.domain.dto.Cliente.ClienteDto;
import com.example.buensaborback.domain.dto.Cliente.ClienteEditDto;
import com.example.buensaborback.domain.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {DomicilioMapper.class})
public interface ClienteMapper extends BaseMapper<Cliente, ClienteDto, ClienteCreateDto, ClienteEditDto> {
}
