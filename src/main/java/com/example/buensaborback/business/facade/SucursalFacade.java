package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dtos.SucursalDTO;
import com.example.buensaborback.domain.dtos.shortDTO.SucursalShortDTO;

public interface SucursalFacade extends BaseFacade<SucursalDTO, Long> {
    SucursalShortDTO getShort(Long id) throws Exception;
}
