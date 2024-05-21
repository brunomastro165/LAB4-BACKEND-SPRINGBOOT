package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ArticuloInsumoFacadeImpl;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoEditDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/ArticuloInsumo")
public class ArticuloInsumoController extends BaseControllerImpl<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoEditDto, Long, ArticuloInsumoFacadeImpl> {
    public ArticuloInsumoController(ArticuloInsumoFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("/buscar/{searchString}")
    public ResponseEntity<List<ArticuloInsumoDto>> getPorLetras(@PathVariable String searchString) {
        List<ArticuloInsumoDto> allArticulos = facade.getAll();
        List<ArticuloInsumoDto> filteredArticulos = allArticulos.stream()
                .filter(a -> a.getDenominacion().contains(searchString) && !a.isEliminado())
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredArticulos);
    }


}
