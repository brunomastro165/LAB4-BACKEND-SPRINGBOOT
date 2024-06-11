package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.PromocionFacadeImpl;
import com.example.buensaborback.business.services.ImagenPromocionService;
import com.example.buensaborback.domain.dto.Promocion.PromocionCreateDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionEditDto;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/promocion")
public class PromocionController extends BaseControllerImpl<Promocion, PromocionDto, PromocionCreateDto, PromocionEditDto, Long, PromocionFacadeImpl> {
    @Autowired
    private ImagenPromocionService imageService;

    public PromocionController(PromocionFacadeImpl facade) {
        super(facade);
    }


    @PutMapping(value = "save/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> edit(@RequestPart("entity") PromocionEditDto entity,
                                  @RequestPart("files") MultipartFile[] files, @PathVariable Long id) {
        try {
            PromocionDto promocion = facade.update(entity, id);
            promocion.setImagenes(imageService.uploadImagesP(files, id));
            return ResponseEntity.ok(promocion);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al actualizar la promoción y subir las imágenes.");
        }

    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> create(@RequestPart("entity") PromocionCreateDto entity,
                                    @RequestPart("files") MultipartFile[] files) {
        try {
            PromocionDto promocion = facade.createNew(entity);
            promocion.setImagenes(imageService.uploadImagesP(files, promocion.getId()));
            return ResponseEntity.ok(promocion);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al crear la promoción y subir las imágenes.");
        }
    }


}
