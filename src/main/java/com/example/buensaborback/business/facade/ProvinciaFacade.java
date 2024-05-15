package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.Provincia.ProvinciaCreateDto;
import com.example.buensaborback.domain.dto.Provincia.ProvinciaDto;

import java.util.List;

public interface ProvinciaFacade extends BaseFacade<ProvinciaDto, ProvinciaCreateDto, ProvinciaCreateDto, Long> {
    List<ProvinciaDto> findByPaisId(Long id);
}
