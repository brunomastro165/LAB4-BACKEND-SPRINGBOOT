package com.example.buensaborback.presentation.rest;


import com.example.buensaborback.business.facade.impl.SucursalFacadeImpl;
import com.example.buensaborback.domain.dto.Sucursal.SucursalCreateDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalEditDto;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sucursal")
@CrossOrigin("*")
public class SucursalController extends BaseControllerImpl<Sucursal, SucursalDto, SucursalCreateDto, SucursalEditDto, Long, SucursalFacadeImpl> {
    public SucursalController(SucursalFacadeImpl facade) {
        super(facade);
    }
}
