package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dtos.EmpresaDTO;
import com.example.buensaborback.domain.dtos.SucursalDTO;
import com.example.buensaborback.domain.dtos.shortDTO.EmpresaShortDTO;
import com.example.buensaborback.domain.dtos.shortDTO.SucursalShortDTO;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface EmpresaFacade extends BaseFacade<EmpresaDTO, Long> {
    EmpresaShortDTO getShort(Long id) throws Exception;

    ResponseEntity<Set<SucursalShortDTO>> getSucursalesByEmpresaId(Long id) throws Exception;
}
