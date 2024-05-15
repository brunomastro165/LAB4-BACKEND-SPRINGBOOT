package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.Empresa.EmpresaCreateDto;
import com.example.buensaborback.domain.dto.Empresa.EmpresaDto;
import com.example.buensaborback.domain.dto.Empresa.EmpresaLargeDto;


public interface EmpresaFacade extends BaseFacade<EmpresaDto, EmpresaCreateDto, EmpresaCreateDto, Long> {
    EmpresaLargeDto getEmpresaSucursales(Long idEmpresa);
}
