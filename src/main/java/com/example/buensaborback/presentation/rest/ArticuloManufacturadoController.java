package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ArticuloManufacturadoFacadeImpl;
import com.example.buensaborback.business.services.ImagenArticuloService;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoEditDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/ArticuloManufacturado")

public class ArticuloManufacturadoController extends BaseControllerImpl<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoEditDto, Long, ArticuloManufacturadoFacadeImpl> {
    @Autowired
    private ImagenArticuloService imageService;

    public ArticuloManufacturadoController(ArticuloManufacturadoFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("/getArticulosManufacturados/{idSucursal}")
    public ResponseEntity<List<ArticuloManufacturadoDto>> getPorString(@RequestParam(required = false) String searchString,
                                                                       @PathVariable Long idSucursal,
                                                                       @RequestParam(required = false) Integer limit,
                                                                       @RequestParam(required = false) Long startId) {
        return ResponseEntity.ok(facade.getPorString(searchString, idSucursal, limit, startId));
    }


    @GetMapping("/buscar/{idSucursal}")
    public ResponseEntity<List<ArticuloManufacturadoDto>> getPorSucursal(@RequestParam(required = false) String searchString, @PathVariable Long idSucursal) {
        return ResponseEntity.ok(facade.getPorSucursal(searchString,idSucursal));
    }

    @PutMapping(value = "/save/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> edit(@RequestPart("entity") ArticuloManufacturadoEditDto entity,
                                  @RequestPart("files") MultipartFile[] files, @PathVariable Long id) {
        try {
            facade.update(entity, id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al actualizar el artículo manufacturado");
        }
        try {
            imageService.uploadImagesA(files, id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al subir las imágenes.");
        }
        return ResponseEntity.ok(facade.getById(id));
    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestPart("entity") ArticuloManufacturadoCreateDto entity, @RequestPart(value = "files", required = false) MultipartFile[] files) {
        try {
            System.out.println("Estoy en controller");
            ArticuloManufacturadoDto articulo = facade.createNew(entity);
            try {
                articulo.setImagenes(imageService.uploadImagesA(files, articulo.getId()));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al subir las imágenes.");
            }
            return ResponseEntity.ok(articulo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al crear el artículo manufacturado.");
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            facade.deleteById(id);
            // Si la entidad no existe (lo que significa que fue borrada correctamente), devuelve un estado 200
            return ResponseEntity.ok("La entidad fue borrada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se ha podido borrar el articulo manufacturado");

        }
    }


}
