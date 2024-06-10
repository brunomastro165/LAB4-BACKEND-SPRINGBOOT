package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.EmpleadoFacadeImpl;
import com.example.buensaborback.business.services.ImagenEmpleadoService;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoCreateDto;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoDto;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/empleado")
public class EmpleadoController extends BaseControllerImpl<Empleado, EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto, Long, EmpleadoFacadeImpl> {
    @Autowired
    private ImagenEmpleadoService imageService;

    public EmpleadoController(EmpleadoFacadeImpl facade) {
        super(facade);
    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestPart("entity") EmpleadoCreateDto entity, @RequestPart("file") MultipartFile file) {
        try {
            System.out.println("Estoy en controller");
            EmpleadoDto empleado = facade.createNew(entity);
            try {
                empleado.setImagenPersona(imageService.uploadImagesE(file, empleado.getId()));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al subir la imagen.");
            }
            return ResponseEntity.ok(empleado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al crear el empleado.");
        }
    }

}
