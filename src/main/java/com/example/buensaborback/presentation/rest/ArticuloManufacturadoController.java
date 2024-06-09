package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ArticuloManufacturadoFacadeImpl;
import com.example.buensaborback.business.services.ImagenArticuloService;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoEditDto;
import com.example.buensaborback.domain.dto.ImagenArticuloDto.ImagenArticuloDto;
import com.example.buensaborback.domain.dto.ImagenPromocion.ImagenPromocionDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionCreateDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleCreateDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import com.example.buensaborback.repositories.ArticuloManufacturadoRepository;
import com.example.buensaborback.repositories.ArticuloRepository;
import com.example.buensaborback.repositories.CategoriaRepository;
import com.example.buensaborback.repositories.ImagenArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
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
    @GetMapping("/getArticulosManufacturados/{searchString}/{idSucursal}/{limit}/{startId}")
    public ResponseEntity<List<ArticuloManufacturadoDto>> getPorString(@PathVariable(required = false) String searchString, @PathVariable Long idSucursal, @PathVariable Integer limit, @PathVariable Long startId) {
        if (limit == null || startId == null) {
            return ResponseEntity.badRequest().build();
        }
        List<ArticuloManufacturadoDto> articulos = facade.getAll();
        List<ArticuloManufacturadoDto> filteredArticulos = articulos.stream()
                .filter(a -> (searchString == null || a.getDenominacion().toLowerCase().contains(searchString.toLowerCase()))
                        && a.getId() > startId
                        && !a.isEliminado()
                        && a.getCategoria() != null
                        && a.getCategoria().getSucursales().stream().anyMatch(s -> s.getId().equals(idSucursal)))
                .collect(Collectors.toList());
        if (filteredArticulos.size() > limit) {
            filteredArticulos = filteredArticulos.subList(0, limit);
        }
        return ResponseEntity.ok(filteredArticulos);
    }

    @GetMapping("/buscar/{idSucursal}")
    public ResponseEntity<List<ArticuloManufacturadoDto>> getPorSucursal(@PathVariable Long idSucursal) {
        List<ArticuloManufacturadoDto> allArticulos = facade.getAll();
        List<ArticuloManufacturadoDto> filteredArticulos = allArticulos.stream()
                .filter(a -> !a.isEliminado()
                        && a.getCategoria().getSucursales().stream().anyMatch(s -> s.getId().equals(idSucursal)))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredArticulos);
    }

    @GetMapping("/getArticulosCategoria/{idCategoria}")
    public ResponseEntity<List<ArticuloManufacturadoDto>> getPorCategorias(@PathVariable Long idCategoria) {
        List<ArticuloManufacturadoDto> allArticulos = facade.getAll();
        List<ArticuloManufacturadoDto> filteredArticulos = allArticulos.stream()
                .filter(a -> a.getCategoria().getId().equals(idCategoria)
                        && !a.isEliminado())
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredArticulos);
    }
    @PutMapping(value = "save/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> edit(@RequestPart("entity") ArticuloManufacturadoEditDto entity,
                                  @RequestPart("files") MultipartFile[] files, @PathVariable Long id) {
        try {
            facade.update(entity,id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al actualizar el artículo manufacturado");
        }

        try {
            imageService.uploadImagesA(files, id);
        }catch (Exception e){
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
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al subir las imágenes.");
            }

            return ResponseEntity.ok(articulo);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al crear el artículo manufacturado.");
        }
    }


}
