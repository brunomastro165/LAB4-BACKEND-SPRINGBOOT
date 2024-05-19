package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.CategoriaFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Categoria.CategoriaCreateDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaDto;
import com.example.buensaborback.domain.entities.Categoria;

public class CategoriaFacadeImpl extends BaseFacadeImpl<Categoria, CategoriaDto, CategoriaCreateDto, CategoriaCreateDto, Long> implements CategoriaFacade {
    public CategoriaFacadeImpl(BaseService<Categoria, Long> baseService, BaseMapper<Categoria, CategoriaDto, CategoriaCreateDto, CategoriaCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
