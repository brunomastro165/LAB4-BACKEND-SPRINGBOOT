package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.ArticuloManufacturadoFacade;
import com.example.buensaborback.business.facade.PromocionFacade;
import com.example.buensaborback.business.facade.impl.ArticuloInsumoFacadeImpl;
import com.example.buensaborback.business.services.ArticuloManufacturadoService;
import com.example.buensaborback.business.services.ImagenArticuloService;
import com.example.buensaborback.domain.dto.Articulo.ArticuloDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoEditDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoEditDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.example.buensaborback.domain.dto.ImagenArticuloDto.ImagenArticuloDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturadoDetalle;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import com.example.buensaborback.repositories.ArticuloRepository;
import com.example.buensaborback.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/ArticuloInsumo")
public class ArticuloInsumoController extends BaseControllerImpl<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoEditDto, Long, ArticuloInsumoFacadeImpl> {

    @Autowired
    private ImagenArticuloService imageService;

    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private ArticuloManufacturadoFacade articuloManufacturadoFacade;
    @Autowired
    private PromocionFacade promocionFacade;

    public ArticuloInsumoController(ArticuloInsumoFacadeImpl facade) {
        super(facade);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping("/getArticulosCategoria/{idCategoria}")
    public ResponseEntity<List<ArticuloInsumoDto>> getPorCategorias(@PathVariable Long idCategoria) {
        List<ArticuloInsumoDto> allArticulos = facade.getAll();
        List<ArticuloInsumoDto> filteredArticulos = allArticulos.stream()
                .filter(a -> a.getCategoria().getId().equals(idCategoria)
                        && !a.isEliminado())
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredArticulos);
    }

    @GetMapping("/getArticulos/{searchString}/{idSucursal}")
    public ResponseEntity<List<Articulo>> getAllArticulos(@PathVariable String searchString, @PathVariable Long idSucursal) {
        List<Articulo> articulos = articuloRepository.getAll();
        List<Articulo> filteredArticulos = articulos.stream()
                .filter(a -> a.getDenominacion().toLowerCase().contains(searchString.toLowerCase())
                        && !a.isEliminado()
                        && a.getCategoria() != null
                        && a.getCategoria().getSucursales().stream().anyMatch(s -> s.getId().equals(idSucursal)))
                .collect(Collectors.toList());
        List<Articulo> articulosResponse = new ArrayList<>();

        // Establece la categoría e imágenes en null después de filtrar
        for (Articulo articulo : filteredArticulos) {
            ArticuloInsumo articuloInsumo = new ArticuloInsumo();
            articuloInsumo.setUnidadMedida(articulo.getUnidadMedida());
            articuloInsumo.setPrecioCompra(articulo.getPrecioVenta());
            articuloInsumo.setDenominacion(articulo.getDenominacion());
            articuloInsumo.setId(articulo.getId());
            articuloInsumo.setEliminado(articulo.isEliminado());
            articulosResponse.add(articuloInsumo);
        }

        return ResponseEntity.ok(articulosResponse);
    }
    @GetMapping("/getArticulosInsumos/{searchString}/{idSucursal}/{limit}/{startId}")
    public ResponseEntity<List<ArticuloInsumoDto>> getPorString(@PathVariable(required = false) String searchString, @PathVariable Long idSucursal, @PathVariable Integer limit, @PathVariable Long startId) {
        if (limit == null || startId == null) {
            return ResponseEntity.badRequest().build();
        }
        List<ArticuloInsumoDto> articulos = facade.getAll();
        List<ArticuloInsumoDto> filteredArticulos = articulos.stream()
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

    @GetMapping("/buscar/{searchString}/{idSucursal}")
    public ResponseEntity<List<ArticuloInsumoDto>> getPorLetras(@PathVariable String searchString, @PathVariable Long idSucursal) {
        List<ArticuloInsumoDto> allArticulos = facade.getAll();
        List<ArticuloInsumoDto> filteredArticulos = allArticulos.stream()
                .filter(a -> a.getDenominacion().toLowerCase().contains(searchString.toLowerCase())
                        && !a.isEliminado()
                        && a.getCategoria().getSucursales().stream().anyMatch(s -> s.getId().equals(idSucursal)))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredArticulos);
    }
    @PutMapping(value = "save/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> edit(@RequestPart("entity") ArticuloInsumoEditDto entity,
                                                         @RequestPart("files") MultipartFile[] files, @PathVariable Long id) {
        try {
            facade.update(entity,id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al subir actualizar el insumo");
        }

        try {
            imageService.uploadImagesA(files, id);
        }catch (Exception e){
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
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al subir las imágenes.");
            }
            return ResponseEntity.ok(articulo);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        //Aca se comprueba que el insumo no esta en un manufacturado
        boolean isInManufacturado = articuloManufacturadoFacade.getAll().stream()
                .flatMap(articuloManufacturado -> articuloManufacturado.getArticuloManufacturadoDetalles().stream())
                .anyMatch(articuloManufacturadoDetalle -> articuloManufacturadoDetalle.getArticuloInsumo().getId() == id);
        if (isInManufacturado) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se puede borrar el insumo porque esta en un manufacturado");
        }
        //Aca se comprueba que el insumo no esta en una promocion
        boolean isInPromocion = promocionFacade.getAll().stream()
                .flatMap(promocion -> promocion.getDetalles().stream())
                .anyMatch(promocionDetalle ->
                        promocionDetalle.getInsumos() != null && promocionDetalle.getInsumos().isEliminado()
                );
        if (isInPromocion) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se puede borrar el insumo porque esta en una promocion");
        }
        facade.deleteById(id);
        return ResponseEntity.ok("La entidad fue borrada correctamente");
    }


}
