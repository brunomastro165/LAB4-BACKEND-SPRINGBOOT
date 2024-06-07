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
    @Autowired
    private ImagenArticuloRepository imagenArticuloRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ArticuloManufacturadoRepository articuloRepository;

    public ArticuloManufacturadoController(ArticuloManufacturadoFacadeImpl facade) {
        super(facade);
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
    public ResponseEntity<ArticuloManufacturadoDto> edit(@RequestPart("entity") ArticuloManufacturadoEditDto entity,
                                                         @RequestPart("files") MultipartFile[] files, @PathVariable Long id) {
        facade.update(entity,id);
        imageService.uploadImagesA(files, id);

        return ResponseEntity.ok(facade.getById(id));
    }


    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticuloManufacturadoDto> create(@RequestPart("entity") ArticuloManufacturadoCreateDto entity, @RequestPart("files") MultipartFile[] files) {
        try {
            System.out.println("Estoy en controller");
            System.out.println(entity.getId());
            ArticuloManufacturadoDto articulo = facade.createNew(entity);
            articulo.setImagenes(imageService.uploadImagesA(files, articulo.getId()));
            return ResponseEntity.ok(articulo);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
