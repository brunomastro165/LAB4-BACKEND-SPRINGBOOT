package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dtos.EmpresaDTO;
import com.example.buensaborback.domain.dtos.shortDTO.EmpresaShortDTO;

public interface EmpresaFacade extends BaseFacade<EmpresaDTO, Long> {
    EmpresaShortDTO getShort(Long id) throws Exception;
}
