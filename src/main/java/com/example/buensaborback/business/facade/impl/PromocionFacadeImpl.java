package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.PromocionFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.CategoriaService;
import com.example.buensaborback.business.services.PromocionService;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Categoria.CategoriaDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionCreateDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionEditDto;
import com.example.buensaborback.domain.entities.Promocion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromocionFacadeImpl extends BaseFacadeImpl<Promocion, PromocionDto, PromocionCreateDto, PromocionEditDto, Long> implements PromocionFacade {
    public PromocionFacadeImpl(BaseService<Promocion, Long> baseService, BaseMapper<Promocion, PromocionDto, PromocionCreateDto, PromocionEditDto> baseMapper) {
        super(baseService, baseMapper);
    }
}