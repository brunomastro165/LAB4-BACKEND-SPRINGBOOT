package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.SucursalFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.SucursalMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dtos.SucursalDTO;
import com.example.buensaborback.domain.dtos.shortDTO.SucursalShortDTO;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalFacadeImpl extends BaseFacadeImpl<Sucursal, SucursalDTO, Long> implements SucursalFacade {


    private final SucursalMapper sucursalMapper;

    public SucursalFacadeImpl(BaseService<Sucursal, Long> baseService, SucursalMapper sucursalMapper) {
        super(baseService, sucursalMapper);
        this.sucursalMapper = sucursalMapper;
    }

    @Override
    public SucursalShortDTO getShort(Long id) throws Exception {
        Sucursal entity = baseService.findById(id);
        return sucursalMapper.toShortDTO(entity);
    }

    public SucursalShortDTO saveShort(SucursalShortDTO sucursalShortDTO) throws Exception {
        Sucursal entity = sucursalMapper.toEntityFromShortDTO(sucursalShortDTO);
        return sucursalMapper.toShortDTO(baseService.save(entity));
    }

    public void deleteShort(Long id) throws Exception {
        baseService.delete(id);
    }

    public List<SucursalShortDTO> findAllShort() throws Exception {
        List<Sucursal> entities = baseService.findAll();
        return sucursalMapper.toShortDTOsList(entities);
    }

    public SucursalShortDTO updateShort(Long id, SucursalShortDTO sucursalShortDTO) throws Exception {
        Sucursal entity = sucursalMapper.toEntityFromShortDTO(sucursalShortDTO);
        return sucursalMapper.toShortDTO(baseService.update(id, entity));
    }
}