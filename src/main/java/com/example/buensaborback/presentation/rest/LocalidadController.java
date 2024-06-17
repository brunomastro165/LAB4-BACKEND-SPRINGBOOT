package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.LocalidadFacadeImpl;
import com.example.buensaborback.domain.dto.Localidad.LocalidadCreateDto;
import com.example.buensaborback.domain.dto.Localidad.LocalidadDto;
import com.example.buensaborback.domain.entities.Localidad;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localidad")
@CrossOrigin("*")
public class LocalidadController extends BaseControllerImpl<Localidad, LocalidadDto, LocalidadCreateDto, LocalidadCreateDto, Long, LocalidadFacadeImpl> {

    private static final Logger logger = LoggerFactory.getLogger(LocalidadController.class);

    public LocalidadController(LocalidadFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("/findByProvincia/{idProvincia}")
    public ResponseEntity<List<LocalidadDto>> getByProvincia(@PathVariable Long idProvincia) {
        logger.info("INICIO GET BY PROVINCIA");
        return ResponseEntity.ok(facade.findByProvinciaId(idProvincia));
    }
}
