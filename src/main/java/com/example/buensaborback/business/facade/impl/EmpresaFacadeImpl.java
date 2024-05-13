package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.EmpresaFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.EmpresaMapper;
import com.example.buensaborback.business.services.EmpresaService;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.EmpresaDTO;
import com.example.buensaborback.domain.dtos.SucursalDTO;
import com.example.buensaborback.domain.dtos.shortDTO.EmpresaShortDTO;
import com.example.buensaborback.domain.dtos.shortDTO.SucursalShortDTO;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.repositories.EmpresaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EmpresaFacadeImpl extends BaseFacadeImpl<Empresa, EmpresaDTO, Long> implements EmpresaFacade {
    private final EmpresaMapper empresaMapper;

    public EmpresaFacadeImpl(BaseService<Empresa, Long> baseService, EmpresaMapper empresaMapper) {
        super(baseService, empresaMapper);
        this.empresaMapper = empresaMapper;
    }

    @Override
    public EmpresaShortDTO getShort(Long id) throws Exception {
        Empresa entity = baseService.findById(id);
        return empresaMapper.toShortDTO(entity);
    }

    public EmpresaShortDTO saveShort(EmpresaShortDTO empresaShortDTO) throws Exception {
        Empresa entity = empresaMapper.toEntityFromShortDTO(empresaShortDTO);
        return empresaMapper.toShortDTO(baseService.save(entity));
    }

    public void deleteShort(Long id) throws Exception {
        baseService.delete(id);
    }

    public List<EmpresaShortDTO> findAllShort() throws Exception {
        List<Empresa> entities = baseService.findAll();
        return empresaMapper.toShortDTOsList(entities);
    }

    public EmpresaShortDTO updateShort(Long id, EmpresaShortDTO empresaShortDTO) throws Exception {
        Empresa entity = empresaMapper.toEntityFromShortDTO(empresaShortDTO);
        return empresaMapper.toShortDTO(baseService.update(id, entity));
    }
    @Override
    public ResponseEntity<Set<SucursalShortDTO>> getSucursalesByEmpresaId(Long id) throws Exception {
        Empresa entity = baseService.findById(id);
        Set<SucursalShortDTO> sucursalesDTO = empresaMapper.toSucursalDTOsList(entity.getSucursales());
        return ResponseEntity.ok(sucursalesDTO);
    }
}

