package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.UsuarioEmpleadoFacadeImpl;
import com.example.buensaborback.domain.dto.UsuarioEmpleado.UsuarioEmpleadoCreateDto;
import com.example.buensaborback.domain.dto.UsuarioEmpleado.UsuarioEmpleadoDto;
import com.example.buensaborback.domain.entities.UsuarioEmpleado;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarioEmpleado")
public class UsuarioEmpleadoController extends BaseControllerImpl<UsuarioEmpleado, UsuarioEmpleadoDto, UsuarioEmpleadoCreateDto, UsuarioEmpleadoCreateDto, Long, UsuarioEmpleadoFacadeImpl> {
    public UsuarioEmpleadoController(UsuarioEmpleadoFacadeImpl facade) {
        super(facade);
    }

}
