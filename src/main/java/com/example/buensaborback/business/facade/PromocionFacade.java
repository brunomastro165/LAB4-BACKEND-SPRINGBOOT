package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.base.BaseFacade;
import com.example.buensaborback.domain.dto.Categoria.CategoriaDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionCreateDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionEditDto;

public interface PromocionFacade extends BaseFacade<PromocionDto, PromocionCreateDto, PromocionEditDto, Long> {

}
