package com.example.buensaborback.business.facade.impl;

import com.example.buensaborback.business.facade.EmpresaFacade;
import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.mapper.EmpresaMapper;
import com.example.buensaborback.business.services.EmpresaService;
import com.example.buensaborback.business.services.base.BaseService;
import com.example.buensaborback.domain.dto.Empresa.EmpresaCreateDto;
import com.example.buensaborback.domain.dto.Empresa.EmpresaDto;
import com.example.buensaborback.domain.dto.Empresa.EmpresaLargeDto;
import com.example.buensaborback.domain.entities.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaFacadeImpl extends BaseFacadeImpl<Empresa, EmpresaDto, EmpresaCreateDto, EmpresaCreateDto, Long> implements EmpresaFacade {

    @Autowired
    EmpresaMapper empresaMapper;
    @Autowired
    EmpresaService empresaService;

    public EmpresaFacadeImpl(BaseService<Empresa, Long> baseService, BaseMapper<Empresa, EmpresaDto, EmpresaCreateDto, EmpresaCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Override
    public EmpresaLargeDto getEmpresaSucursales(Long idEmpresa) {
        return empresaMapper.toLargeDto(empresaService.getEmpresaSucursales(idEmpresa));
    }
}
