package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ArticuloInsumoFacadeImpl;
import com.example.buensaborback.business.services.ImagenArticuloService;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoEditDto;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/ArticuloInsumo")
public class ArticuloInsumoController extends BaseControllerImpl<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoEditDto, Long, ArticuloInsumoFacadeImpl> {

    @Autowired
    private ImagenArticuloService imageService;

    public ArticuloInsumoController(ArticuloInsumoFacadeImpl facade) {
        super(facade);
    }


    @GetMapping("/getArticulos/{idSucursal}")
    public ResponseEntity<List<Articulo>> getAllArticulos(@RequestParam(required = false) String searchString, @PathVariable Long idSucursal, @RequestParam(required = false) Integer limit, @RequestParam(required = false) Long startId) {

        return ResponseEntity.ok(facade.getAllArticulos(searchString, idSucursal, limit, startId));
    }

    @GetMapping("/getArticulosInsumos/{idSucursal}")
    public ResponseEntity<List<ArticuloInsumoDto>> getPorString(@RequestParam(required = false) String searchString, @PathVariable Long idSucursal, @RequestParam(required = false) Integer limit, @RequestParam(required = false) Long startId) {
        return ResponseEntity.ok(facade.filtrarArticulos(searchString, idSucursal, limit, startId));
    }

    @GetMapping("/buscar/{idSucursal}")
    public ResponseEntity<List<ArticuloInsumoDto>> getPorLetras(@RequestParam(required = false) String searchString, @PathVariable Long idSucursal, @RequestParam(required = false) Integer limit, @RequestParam(required = false) Long startId) {
        return ResponseEntity.ok(facade.filtrarArticulos(searchString, idSucursal, limit, startId));
    }


    @PutMapping(value = "save/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> edit(@RequestPart("entity") ArticuloInsumoEditDto entity,
                                  @RequestPart("files") MultipartFile[] files, @PathVariable Long id) {
        try {
            facade.update(entity, id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al subir actualizar el insumo");
        }
        try {
            imageService.uploadImagesA(files, id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al subir las imágenes.");
        }


        return ResponseEntity.ok(facade.getById(id));
    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestPart("entity") ArticuloInsumoCreateDto entity, @RequestPart(value = "files", required = false) MultipartFile[] files) {
        try {
            System.out.println("Estoy en controller");
            ArticuloInsumoDto articulo = facade.createNew(entity);
            try {
                articulo.setImagenes(imageService.uploadImagesA(files, articulo.getId()));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al subir las imágenes.");
            }
            return ResponseEntity.ok(articulo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            facade.deleteById(id);
            return ResponseEntity.ok("La entidad fue borrada correctamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se puede borrar el insumo porque esta en una promocion o en un articulo manufacturado");
        }


    }


}
