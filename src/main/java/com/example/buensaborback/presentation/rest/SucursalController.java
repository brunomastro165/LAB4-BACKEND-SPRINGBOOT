package com.example.buensaborback.presentation.rest;


import com.example.buensaborback.business.facade.impl.SucursalFacadeImpl;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalCreateDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalEditDto;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sucursal")
@CrossOrigin("*")
public class SucursalController extends BaseControllerImpl<Sucursal, SucursalDto, SucursalCreateDto, SucursalEditDto, Long, SucursalFacadeImpl> {
    public SucursalController(SucursalFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("/getInsumos/{idSucursal}")
    public ResponseEntity<List<ArticuloInsumoDto>> getArticulos(@PathVariable Long idSucursal) {
        List<ArticuloInsumoDto> insumos = new ArrayList<>();
        var categorias = facade.findAllCategoriasByIdSucursal(idSucursal);
        for (CategoriaDto categoria :
                categorias) {
            for (ArticuloInsumoDto insumo :
                    categoria.getInsumos()) {
                insumos.add(insumo);
            }
        }
        return ResponseEntity.ok(insumos);
    }

    @GetMapping("/getCategorias/{idSucursal}")
    public ResponseEntity<List<CategoriaDto>> getCategorias(@PathVariable Long idSucursal) {
        return ResponseEntity.ok(facade.findAllCategoriasByIdSucursal(idSucursal));
    }
}
