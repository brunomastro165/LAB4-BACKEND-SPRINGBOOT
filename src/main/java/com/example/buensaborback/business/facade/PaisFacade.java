package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.Pais.PaisCreateDto;
import com.example.buensaborback.domain.dto.Pais.PaisDto;

public interface PaisFacade extends BaseFacade<PaisDto, PaisCreateDto, PaisCreateDto, Long> {
}
