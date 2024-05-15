package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.Localidad.LocalidadCreateDto;
import com.example.buensaborback.domain.dto.Localidad.LocalidadDto;

import java.util.List;

public interface LocalidadFacade extends BaseFacade<LocalidadDto, LocalidadCreateDto, LocalidadCreateDto, Long> {

    List<LocalidadDto> findByProvinciaId(Long id);
}
