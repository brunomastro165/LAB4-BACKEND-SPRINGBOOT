package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ClienteFacadeImpl;
import com.example.buensaborback.business.services.ImagenClienteService;
import com.example.buensaborback.domain.dto.Cliente.ClienteCreateDto;
import com.example.buensaborback.domain.dto.Cliente.ClienteDto;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/cliente")
public class ClienteController extends BaseControllerImpl<Cliente, ClienteDto, ClienteCreateDto, ClienteCreateDto, Long, ClienteFacadeImpl> {
    @Autowired
    private ImagenClienteService imageService;

    public ClienteController(ClienteFacadeImpl facade) {
        super(facade);
    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDto> create(@RequestPart("entity") ClienteCreateDto entity, @RequestPart("file") MultipartFile file) {
        try {
            System.out.println("Estoy en controller");
            ClienteDto cliente = facade.createNew(entity);
            cliente.setImagenCliente(imageService.uploadImagesC(file, cliente.getId()));
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }
}
