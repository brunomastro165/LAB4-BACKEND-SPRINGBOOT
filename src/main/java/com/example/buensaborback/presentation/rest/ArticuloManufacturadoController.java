package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ArticuloManufacturadoFacadeImpl;
import com.example.buensaborback.business.services.ImagenArticuloService;
import com.example.buensaborback.business.services.impl.ImagenArticuloServiceImpl;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoEditDto;
import com.example.buensaborback.domain.dto.ImageModel;
import com.example.buensaborback.domain.dto.ImagenArticuloDto.ImagenArticuloDto;
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
    public ArticuloManufacturadoController(ArticuloManufacturadoFacadeImpl facade) {
        super(facade);
    }
    @Autowired
    ImagenArticuloService imagenArticuloService;
    ArticuloManufacturadoFacadeImpl articuloManufacturadoFacadeImpl;
    ImagenArticuloServiceImpl imagenArticuloServiceImpl;
/*
    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticuloManufacturadoDto> Create(
            @RequestPart("articuloManufacturado") String articuloManufacturadoJson,
            @RequestPart("imagen") MultipartFile[] files) {
        // Convertir el JSON de articuloInsumo a ArticuloInsumoDTO
        ArticuloManufacturadoCreateDto articuloManufacturadoCreateDto = articuloManufacturadoFacadeImpl.mapperJson(articuloManufacturadoJson);
        // Subir las imágenes y obtener las URLs
        List<String> imageUrls = imagenArticuloServiceImpl.uploadImages(files);
        // Asignar las URLs de las imágenes al DTO
        articuloManufacturadoCreateDto.setImagenes(imageUrls);
        // Crear el ArticuloInsumo
        ArticuloManufacturadoDto createdArticulo = articuloManufacturadoFacadeImpl.createNew(articuloManufacturadoCreateDto);

        return ResponseEntity.ok(createdArticulo);

    }
*/


}
