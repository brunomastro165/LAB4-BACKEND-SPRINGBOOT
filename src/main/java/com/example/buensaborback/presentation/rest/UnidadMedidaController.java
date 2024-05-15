package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.UnidadMedidaFacadeImpl;
import com.example.buensaborback.domain.dto.UnidadMedida.UnidadMedidaCreateDto;
import com.example.buensaborback.domain.dto.UnidadMedida.UnidadMedidaDto;
import com.example.buensaborback.domain.entities.UnidadMedida;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/UnidadMedida")
@CrossOrigin("*")
public class UnidadMedidaController extends BaseControllerImpl<UnidadMedida, UnidadMedidaDto, UnidadMedidaCreateDto, UnidadMedidaCreateDto, Long, UnidadMedidaFacadeImpl> {
    public UnidadMedidaController(UnidadMedidaFacadeImpl facade) {
        super(facade);
    }
}
