package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.ClienteFacadeImpl;
import com.example.buensaborback.business.services.ClienteService;
import com.example.buensaborback.business.services.ImagenClienteService;
import com.example.buensaborback.business.services.UsuarioClienteService;
import com.example.buensaborback.domain.dto.Cliente.ClienteCreateDto;
import com.example.buensaborback.domain.dto.Cliente.ClienteDto;
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
@CrossOrigin(value = "*",allowedHeaders = "*")
public class ClienteController extends BaseControllerImpl<Cliente, ClienteDto, ClienteCreateDto, ClienteCreateDto, Long, ClienteFacadeImpl> {
    @Autowired
    private ImagenClienteService imageService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UsuarioClienteService usuarioClienteService;

    public ClienteController(ClienteFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("/exists/{nombreUsuario}")
    public ResponseEntity<Boolean> existsByUsername(@PathVariable String nombreUsuario){
        return ResponseEntity.ok().body(usuarioClienteService.existsByUsername(nombreUsuario));
    }

    @PostMapping("/getCliente")
    public ResponseEntity<Cliente> getCliente(@RequestBody Cliente cliente) {
        try {
            var items = clienteService.getAll();
            for (Cliente c : items) {
                // Encriptar la clave ingresada usando SHA3
                SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
                byte[] digest = digestSHA3.digest(cliente.getUsuario().getClave().getBytes());

                // Convertir el hash a hexadecimal
                String claveEncriptada = Hex.toHexString(digest);

                if (c.getUsuario().getUserName().equals(cliente.getUsuario().getUserName()) && c.getUsuario().getClave().equals(claveEncriptada)) {
                    return ResponseEntity.ok().body(c);
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> crear(@RequestBody Cliente cliente){
        var clientes = clienteService.getAll();

        if (!clientes.isEmpty()) {
            for (Cliente c : clientes) {
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

        return ResponseEntity.ok().body(clienteService.create(cliente));
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

