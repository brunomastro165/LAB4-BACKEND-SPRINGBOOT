package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ArticuloInsumoFacadeImpl;
import com.example.buensaborback.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.Insumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/ArticuloInsumo")
public class ArticuloInsumoController extends BaseControllerImpl<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoCreateDto, Long, ArticuloInsumoFacadeImpl> {
    public ArticuloInsumoController(ArticuloInsumoFacadeImpl facade) {
        super(facade);
    }
}
