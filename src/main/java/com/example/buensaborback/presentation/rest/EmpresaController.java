package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.EmpresaFacadeImpl;
import com.example.buensaborback.domain.dto.Empresa.EmpresaCreateDto;
import com.example.buensaborback.domain.dto.Empresa.EmpresaDto;
import com.example.buensaborback.domain.dto.Empresa.EmpresaLargeDto;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
@CrossOrigin("*")
public class EmpresaController extends BaseControllerImpl<Empresa, EmpresaDto, EmpresaCreateDto, EmpresaCreateDto, Long, EmpresaFacadeImpl> {
    public EmpresaController(EmpresaFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("/sucursales/{idEmpresa}")
    public ResponseEntity<EmpresaLargeDto> getEmpresaSucursales(@PathVariable Long idEmpresa) {
        return ResponseEntity.ok(facade.getEmpresaSucursales(idEmpresa));
    }
}
