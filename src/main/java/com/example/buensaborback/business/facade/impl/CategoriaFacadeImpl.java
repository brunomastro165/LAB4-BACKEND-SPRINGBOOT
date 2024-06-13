package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.CategoriaFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.ArticuloInsumoMapper;
import com.example.buensaborback.business.mapper.ArticuloManufacturadoMapper;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.CategoriaService;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaCreateDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaEditDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaShortDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoriaFacadeImpl extends BaseFacadeImpl<Categoria, CategoriaDto, CategoriaCreateDto, CategoriaEditDto, Long> implements CategoriaFacade {
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    ArticuloInsumoMapper articuloInsumoMapper;

    @Autowired
    ArticuloManufacturadoMapper articuloManufacturadoMapper;

    public CategoriaFacadeImpl(BaseService<Categoria, Long> baseService, BaseMapper<Categoria, CategoriaDto, CategoriaCreateDto, CategoriaEditDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Override
    public CategoriaDto addInsumo(Long idCategoria, Long idInsumo) {
        return baseMapper.toDTO(categoriaService.addArticulo(idCategoria, idInsumo));
    }

    @Override
    public CategoriaDto addManufacturado(Long idCategoria, Long idManufacturado) {
        return baseMapper.toDTO(categoriaService.addArticulo(idCategoria, idManufacturado));
    }

    @Override
    public CategoriaDto addSubCategoria(Long idCategoria, CategoriaCreateDto subCategoria) {
        Categoria subCategoriaToCreate = baseMapper.toEntityCreate(subCategoria);
        return baseMapper.toDTO(categoriaService.addSubCategoria(idCategoria, subCategoriaToCreate));
    }
    @Override
    public List<ArticuloManufacturadoDto> getManufacturadoSubCategoria(Long idSub,String searchString) {
        List<ArticuloManufacturadoDto> articuloManufacturadoDtos = new ArrayList<>();
        for (ArticuloManufacturado articuloManufacturado:
        categoriaService.getManufacturadoSubCategoria(idSub,searchString)) {
            articuloManufacturadoDtos.add(articuloManufacturadoMapper.toDTO(articuloManufacturado));
        }
        return articuloManufacturadoDtos;
    }
    @Override
    public List<ArticuloInsumoDto> getInsumoSubCategoria(Long idSub,String searchString) {
        List<ArticuloInsumoDto> articuloInsumoDtos = new ArrayList<>();
        for (ArticuloInsumo articuloInsumo:
                categoriaService.getInsumoSubCategoria(idSub,searchString)) {
            articuloInsumoDtos.add(articuloInsumoMapper.toDTO(articuloInsumo));
        }
        return articuloInsumoDtos;
    }
}
