package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ArticuloManufacturadoFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.ArticuloManufacturadoService;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoEditDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloManufacturadoFacadeImpl extends BaseFacadeImpl<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoEditDto, Long> implements ArticuloManufacturadoFacade {
    @Autowired
    ArticuloManufacturadoService articuloManufacturadoService;

    public ArticuloManufacturadoFacadeImpl(BaseService<ArticuloManufacturado, Long> baseService, BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoEditDto> baseMapper) {
        super(baseService, baseMapper);
    }

    public List<ArticuloManufacturadoDto> getPorString(String searchString, Long idSucursal, Integer limit, Long startId) {
        return baseMapper.toDTOsList(articuloManufacturadoService.getPorString(searchString, idSucursal, limit, startId));
    }
    public List<ArticuloManufacturadoDto> getPorSucursal( String searchString, Long idSucursal){
        return baseMapper.toDTOsList(articuloManufacturadoService.getPorSucursal(searchString,idSucursal));
    }
}
