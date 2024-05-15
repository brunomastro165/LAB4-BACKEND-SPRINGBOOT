package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.UnidadMedidaFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.UnidadMedida.UnidadMedidaCreateDto;
import com.example.buensaborback.domain.dto.UnidadMedida.UnidadMedidaDto;
import com.example.buensaborback.domain.entities.UnidadMedida;
import org.springframework.stereotype.Service;

@Service
public class UnidadMedidaFacadeImpl extends BaseFacadeImpl<UnidadMedida, UnidadMedidaDto, UnidadMedidaCreateDto, UnidadMedidaCreateDto, Long> implements UnidadMedidaFacade {
    public UnidadMedidaFacadeImpl(BaseService<UnidadMedida, Long> baseService, BaseMapper<UnidadMedida, UnidadMedidaDto, UnidadMedidaCreateDto, UnidadMedidaCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
