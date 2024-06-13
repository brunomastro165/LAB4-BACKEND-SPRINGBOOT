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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticuloManufacturadoFacadeImpl extends BaseFacadeImpl<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoEditDto, Long> implements ArticuloManufacturadoFacade {
    @Autowired
    ArticuloManufacturadoService articuloManufacturadoService;
    public ArticuloManufacturadoFacadeImpl(BaseService<ArticuloManufacturado, Long> baseService, BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoEditDto> baseMapper) {
        super(baseService, baseMapper);
    }
    public List<ArticuloManufacturadoDto> getPorString(String searchString, Long idSucursal, Integer limit,  Long startId){
        return baseMapper.toDTOsList(articuloManufacturadoService.getPorString(searchString,idSucursal,limit,startId));
    }

}
