package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ClienteFacadeImpl;
import com.example.buensaborback.business.services.ImagenClienteService;
import com.example.buensaborback.business.services.UsuarioClienteService;
import com.example.buensaborback.domain.dto.Cliente.ClienteCreateDto;
import com.example.buensaborback.domain.dto.Cliente.ClienteDto;
import com.example.buensaborback.domain.dto.Cliente.ClienteEditDto;
import com.example.buensaborback.domain.dto.UsuarioCliente.UsuarioClienteDto;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/cliente")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class ClienteController extends BaseControllerImpl<Cliente, ClienteDto, ClienteCreateDto, ClienteEditDto, Long, ClienteFacadeImpl> {
    @Autowired
    private ImagenClienteService imageService;
    @Autowired
    private UsuarioClienteService usuarioClienteService;

    public ClienteController(ClienteFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("/exists/{nombreUsuario}")
    public ResponseEntity<Boolean> existsByUsername(@PathVariable String nombreUsuario) {
        return ResponseEntity.ok().body(usuarioClienteService.existsByUsername(nombreUsuario));
    }

    @PostMapping("/getCliente")
    public ResponseEntity<ClienteDto> getCliente(@RequestBody UsuarioClienteDto usuarioCliente) {
        try {
            var items = facade.getAll();
            for (ClienteDto c : items) {
                // Encriptar la clave ingresada usando SHA3
                SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
                byte[] digest = digestSHA3.digest(usuarioCliente.getClave().getBytes());

                // Convertir el hash a hexadecimal
                String claveEncriptada = Hex.toHexString(digest);

                if (c.getUsuario().getUserName().equals(usuarioCliente.getUserName()) && c.getUsuario().getClave().equals(claveEncriptada)) {
                    return ResponseEntity.ok().body(c);
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> crear(@RequestBody ClienteCreateDto cliente) {
        var clientes = facade.getAll();
        if (!clientes.isEmpty()) {
            for (ClienteDto c : clientes) {
                if (c.getUsuario().getUserName().equals(cliente.getUsuario().getUserName())) {
                    return new ResponseEntity<>("Ya existe un cliente con ese nombre", HttpStatus.BAD_REQUEST);
                }
            }
        }

        // Encriptar la clave usando SHA3
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
        byte[] digest = digestSHA3.digest(cliente.getUsuario().getClave().getBytes());

        // Convertir el hash a hexadecimal
        String claveEncriptada = Hex.toHexString(digest);

        // Establecer la clave encriptada
        cliente.getUsuario().setClave(claveEncriptada);

        return ResponseEntity.ok().body(facade.createNew(cliente));
    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestPart("entity") ClienteCreateDto entity, @RequestPart("file") MultipartFile file) {
        try {
            var clientes = facade.getAll();
            System.out.println("Estoy en controller");
            if (!clientes.isEmpty()) {
                for (ClienteDto c : clientes) {
                    if (c.getUsuario().getUserName().equals(entity.getUsuario().getUserName())) {
                        return new ResponseEntity<>("Ya existe un cliente con ese nombre", HttpStatus.BAD_REQUEST);
                    }
                }
            }
            // Encriptar la clave usando SHA3
            SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
            byte[] digest = digestSHA3.digest(entity.getUsuario().getClave().getBytes());

            // Convertir el hash a hexadecimal
            String claveEncriptada = Hex.toHexString(digest);

            // Establecer la clave encriptada
            entity.getUsuario().setClave(claveEncriptada);

            ClienteDto cliente = facade.createNew(entity);
            cliente.setImagenCliente(imageService.uploadImagesC(file, cliente.getId()));

            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al crear el cliente y subir la imagen.");
        }
    }

}

