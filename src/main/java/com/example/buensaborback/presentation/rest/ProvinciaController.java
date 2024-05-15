package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ProvinciaFacadeImpl;
import com.example.buensaborback.domain.dto.Provincia.ProvinciaCreateDto;
import com.example.buensaborback.domain.dto.Provincia.ProvinciaDto;
import com.example.buensaborback.domain.entities.Provincia;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provincia")
@CrossOrigin("*")
public class ProvinciaController extends BaseControllerImpl<Provincia, ProvinciaDto, ProvinciaCreateDto, ProvinciaCreateDto, Long, ProvinciaFacadeImpl> {
    private static final Logger logger = LoggerFactory.getLogger(ProvinciaController.class);

    public ProvinciaController(ProvinciaFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("findByPais/{idPais}")
    public ResponseEntity<List<ProvinciaDto>> getByProvincia(@PathVariable Long idPais) {
        logger.info("INICIO GET BY PROVINCIA");
        return ResponseEntity.ok(facade.findByPaisId(idPais));
    }
}
