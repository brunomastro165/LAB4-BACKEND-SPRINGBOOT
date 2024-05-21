package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.CategoriaFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.CategoriaService;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Categoria.CategoriaCreateDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaEditDto;
import com.example.buensaborback.domain.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaFacadeImpl extends BaseFacadeImpl<Categoria, CategoriaDto, CategoriaCreateDto, CategoriaEditDto, Long> implements CategoriaFacade {
    public CategoriaFacadeImpl(BaseService<Categoria, Long> baseService, BaseMapper<Categoria, CategoriaDto, CategoriaCreateDto, CategoriaEditDto> baseMapper) {
        super(baseService, baseMapper);
    }
    @Autowired
    private CategoriaService categoriaService;
    @Override
    public CategoriaDto addInsumo(Long idCategoria, Long idInsumo) {
        return baseMapper.toDTO(categoriaService.addArticulo(idCategoria,idInsumo));
    }

    @Override
    public CategoriaDto addManufacturado(Long idCategoria, Long idManufacturado) {
        return baseMapper.toDTO(categoriaService.addArticulo(idCategoria,idManufacturado));
    }

    @Override
    public CategoriaDto addSubCategoria(Long idCategoria, CategoriaCreateDto subCategoria) {
        Categoria subCategoriaToCreate = baseMapper.toEntityCreate(subCategoria);
        return baseMapper.toDTO(categoriaService.addSubCategoria(idCategoria, subCategoriaToCreate));
    }
}
