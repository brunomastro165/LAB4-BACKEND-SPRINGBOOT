package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.PaisFacadeImpl;
import com.example.buensaborback.domain.dto.Pais.PaisCreateDto;
import com.example.buensaborback.domain.dto.Pais.PaisDto;
import com.example.buensaborback.domain.entities.Pais;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pais")
@CrossOrigin("*")
public class PaisController extends BaseControllerImpl<Pais, PaisDto, PaisCreateDto, PaisCreateDto, Long, PaisFacadeImpl> {

    public PaisController(PaisFacadeImpl facade) {
        super(facade);
    }
}
