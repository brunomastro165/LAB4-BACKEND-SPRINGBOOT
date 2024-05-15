package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.Insumo.ArticuloInsumoDto;

public interface ArticuloInsumoFacade extends BaseFacade<ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoCreateDto, Long> {
}
