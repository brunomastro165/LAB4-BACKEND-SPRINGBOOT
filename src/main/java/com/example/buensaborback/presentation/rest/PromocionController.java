package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.PromocionFacadeImpl;
import com.example.buensaborback.business.services.ImagenArticuloService;
import com.example.buensaborback.business.services.ImagenPromocionService;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionCreateDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionEditDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleCreateDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import com.example.buensaborback.repositories.ArticuloRepository;
import com.example.buensaborback.repositories.PromocionDetalleRepository;
import com.example.buensaborback.repositories.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/promocion")
public class PromocionController extends BaseControllerImpl<Promocion, PromocionDto, PromocionCreateDto, PromocionEditDto, Long, PromocionFacadeImpl> {
    @Autowired
    private ImagenPromocionService imageService;
    @Autowired
    private PromocionRepository promocionRepository;
    @Autowired
    private PromocionDetalleRepository promocionDetalleRepository;
    @Autowired
    private ArticuloRepository articuloRepository;

    public PromocionController(PromocionFacadeImpl facade) {
        super(facade);
    }
    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PromocionDto> create(@RequestPart("entity") PromocionCreateDto entity,
                                               @RequestPart("files") MultipartFile[] files) {
        System.out.println(entity);
        PromocionDto promocion = facade.createNew(entity);
        Set<PromocionDetalle> detalles = new HashSet<>();
        for (PromocionDetalleCreateDto detalle:
             entity.getDetalles()) {
            detalles.add(new PromocionDetalle(detalle.getCantidad(),articuloRepository.getById(detalle.getIdArticulo())));
        }

        Promocion promocionEntity = promocionRepository.getById(promocion.getId());
        promocionEntity.setDetalles(detalles);
        System.out.println(promocionEntity+"acá");
        promocionRepository.save(promocionEntity); //

        promocion.setImagenes(imageService.uploadImagesP(files, promocion.getId()));
        return ResponseEntity.ok(promocion);
    }

}
