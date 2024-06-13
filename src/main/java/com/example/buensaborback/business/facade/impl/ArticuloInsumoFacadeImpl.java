package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.ArticuloInsumoFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.ArticuloInsumoService;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoEditDto;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloInsumoFacadeImpl extends BaseFacadeImpl<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoEditDto, Long> implements ArticuloInsumoFacade {
    @Autowired
    ArticuloInsumoService articuloInsumoService;
    public ArticuloInsumoFacadeImpl(BaseService<ArticuloInsumo, Long> baseService, BaseMapper<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoEditDto> baseMapper) {
        super(baseService, baseMapper);
    }
    public List<Articulo> getAllArticulos(String searchString , Long idSucursal, Integer limit, Long startId){
        return articuloInsumoService.getAllArticulos(searchString,idSucursal,limit,startId);
    }
    public List<ArticuloInsumoDto> filtrarArticulos(String searchString, Long idSucursal, Integer limit, Long startId){
        return baseMapper.toDTOsList(articuloInsumoService.filtrarArticulos(searchString,idSucursal,limit,startId));
    }


}
