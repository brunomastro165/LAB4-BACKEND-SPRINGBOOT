package com.example.buensaborback.presentation.rest;


import com.example.buensaborback.business.facade.impl.SucursalFacadeImpl;
import com.example.buensaborback.business.services.ImagenSucursalService;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalCreateDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalEditDto;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sucursal")
@CrossOrigin("*")
public class SucursalController extends BaseControllerImpl<Sucursal, SucursalDto, SucursalCreateDto, SucursalEditDto, Long, SucursalFacadeImpl> {
    @Autowired
    private ImagenSucursalService imageService;

    public SucursalController(SucursalFacadeImpl facade) {
        super(facade);
    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SucursalDto> create(@RequestPart("entity") SucursalCreateDto entity, @RequestPart("files") MultipartFile[] files) {
        System.out.println("Estoy en controller");
        SucursalDto sucursal = facade.createNew(entity);
        sucursal.setImagenes(imageService.uploadImagesS(files, sucursal.getId()));
        return ResponseEntity.ok(sucursal);
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

    @GetMapping("/getPromociones/{idSucursal}")
    public ResponseEntity<List<PromocionDto>> getPromociones(@PathVariable Long idSucursal) {
        return ResponseEntity.ok(facade.findAllPromocionesByIdSucursal(idSucursal));
    }
}
