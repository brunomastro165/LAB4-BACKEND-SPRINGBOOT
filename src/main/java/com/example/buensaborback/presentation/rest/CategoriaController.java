package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.CategoriaFacadeImpl;
import com.example.buensaborback.domain.dto.Categoria.CategoriaCreateDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaEditDto;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/categoria")
public class CategoriaController extends BaseControllerImpl<Categoria, CategoriaDto, CategoriaCreateDto, CategoriaEditDto, Long, CategoriaFacadeImpl> {
    public CategoriaController(CategoriaFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("/insumo")
    public ResponseEntity<List<CategoriaDto>> getCategoriasInsumo() {
        List<CategoriaDto> todasLasCategorias = facade.getAll();
        List<CategoriaDto> insumos = todasLasCategorias.stream()
                .filter(a -> a.isEsInsumo())
                .collect(Collectors.toList());
        return ResponseEntity.ok(insumos);
    }

    @GetMapping("/noInsumo")
    public ResponseEntity<List<CategoriaDto>> getCategoriasNoInsumo() {
        List<CategoriaDto> todasLasCategorias = facade.getAll();
        List<CategoriaDto> noInsumos = todasLasCategorias.stream()
                .filter(a -> !a.isEsInsumo())
                .collect(Collectors.toList());
        return ResponseEntity.ok(noInsumos);
    }

    @PutMapping("/addInsumo/{idCategoria}/{idInsumo}")
    public ResponseEntity<CategoriaDto> addArticuloInsumo(@PathVariable Long idCategoria, @PathVariable Long idInsumo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(facade.addInsumo(idCategoria, idInsumo));
    }

    @PutMapping("/addArticuloManufacturado/{idCategoria}/{idArticulo}")
    public ResponseEntity<CategoriaDto> addArticuloManufacturado(@PathVariable Long idCategoria, @PathVariable Long idArticulo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(facade.addManufacturado(idCategoria, idArticulo));
    }

    @PutMapping("/addSubCategoria/{idCategoria}")
    public ResponseEntity<CategoriaDto> addSubCategoria(@PathVariable Long idCategoria, @RequestBody CategoriaCreateDto subCategoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(facade.addSubCategoria(idCategoria, subCategoria));
    }
}
