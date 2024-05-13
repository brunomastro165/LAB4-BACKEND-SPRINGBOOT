package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.PaisFacadeImpl;
import com.example.buensaborback.domain.dtos.PaisDTO;
import com.example.buensaborback.domain.entities.Pais;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/pais")
public class PaisController extends BaseControllerImpl<Pais, PaisDTO, Long, PaisFacadeImpl> {
    public PaisController(PaisFacadeImpl facade) {
        super(facade);
    }
}
