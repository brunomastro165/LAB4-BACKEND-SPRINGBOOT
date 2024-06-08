package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ArticuloInsumoFacadeImpl;
import com.example.buensaborback.business.services.ImagenArticuloService;
import com.example.buensaborback.domain.dto.Articulo.ArticuloDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoEditDto;
import com.example.buensaborback.domain.dto.ImagenArticuloDto.ImagenArticuloDto;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import com.example.buensaborback.repositories.ArticuloRepository;
import com.example.buensaborback.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    public ArticuloInsumoController(ArticuloInsumoFacadeImpl facade) {
        super(facade);
    }

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
    public ResponseEntity<List<ArticuloInsumoDto>> getPorString(@PathVariable String searchString, @PathVariable Long idSucursal, @PathVariable Integer limit, @PathVariable Long startId) {
        List<ArticuloInsumoDto> articulos = facade.getAll();
        List<ArticuloInsumoDto> filteredArticulos = articulos.stream()
                .filter(a -> a.getDenominacion().toLowerCase().contains(searchString.toLowerCase())
                        && a.getId() > startId
                        && !a.isEliminado()
                        && a.getCategoria() != null
                        && a.getCategoria().getSucursales().stream().anyMatch(s -> s.getId().equals(idSucursal)))
                .collect(Collectors.toList());
        filteredArticulos = filteredArticulos.subList(0, limit);

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


    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticuloInsumoDto> create(@RequestPart("entity") ArticuloInsumoCreateDto entity, @RequestPart(value = "files", required = false) MultipartFile[] files) {
        try {
            System.out.println("Estoy en controller");
            ArticuloInsumoDto articulo = facade.createNew(entity);
            List<ImagenArticuloDto> imagenes = imageService.uploadImagesA(files, articulo.getId());
            for (ImagenArticuloDto imagen :
                    imagenes) {
                articulo.getImagenes().add(imagen);
            }
            ArticuloInsumoCreateDto articuloActualizado = entity;
            articuloActualizado.setId(articulo.getId());
            articulo = facade.createNew(articuloActualizado);

            return ResponseEntity.ok(articulo);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
