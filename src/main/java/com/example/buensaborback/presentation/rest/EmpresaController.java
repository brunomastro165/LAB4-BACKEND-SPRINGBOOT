package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.EmpresaFacadeImpl;
import com.example.buensaborback.business.services.ImagenEmpresaService;
import com.example.buensaborback.domain.dto.Empresa.EmpresaCreateDto;
import com.example.buensaborback.domain.dto.Empresa.EmpresaDto;
import com.example.buensaborback.domain.dto.Empresa.EmpresaLargeDto;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/empresa")
@CrossOrigin("*")
public class EmpresaController extends BaseControllerImpl<Empresa, EmpresaDto, EmpresaCreateDto, EmpresaCreateDto, Long, EmpresaFacadeImpl> {
    @Autowired
    private ImagenEmpresaService imageService;

    public EmpresaController(EmpresaFacadeImpl facade) {
        super(facade);
    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestPart("entity") EmpresaCreateDto entity, @RequestPart("files") MultipartFile[] files) {
        try {

            System.out.println("Estoy en controller");
            EmpresaDto empresa = facade.createNew(entity);
            try {
                empresa.setImagenes(imageService.uploadImagesE(files, empresa.getId()));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al subir las imágenes.");
            }
            return ResponseEntity.ok(empresa);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al crear la empresa.");
        }
    }

    @GetMapping("/sucursales/{idEmpresa}")
    public ResponseEntity<EmpresaLargeDto> getEmpresaSucursales(@PathVariable Long idEmpresa) {
        return ResponseEntity.ok(facade.getEmpresaSucursales(idEmpresa));
    }
}
