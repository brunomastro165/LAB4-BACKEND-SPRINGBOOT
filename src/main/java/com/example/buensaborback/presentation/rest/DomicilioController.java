package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.DomicilioFacadeImpl;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioCreateDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioDto;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domicilio")
@CrossOrigin("*")
public class DomicilioController extends BaseControllerImpl<Domicilio, DomicilioDto, DomicilioCreateDto, DomicilioCreateDto, Long, DomicilioFacadeImpl> {
    public DomicilioController(DomicilioFacadeImpl facade) {
        super(facade);
    }

    @PostMapping("/addDomicilioCliente/{idCliente}")
    public DomicilioDto addDomiciliaACliente(@RequestBody DomicilioCreateDto domicilioCreateDto, @PathVariable Long idCliente) {
        return facade.addDomiciliaACliente(domicilioCreateDto, idCliente);
    }
}
