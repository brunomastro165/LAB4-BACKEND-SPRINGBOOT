package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.EmpleadoFacade;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/empleado")
public class EmpleadoController extends BaseControllerImpl<Empleado, EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto, Long, EmpleadoFacadeImpl> {
    @Autowired
    private ImagenEmpleadoService imageService;
    @Autowired
    private EmpleadoFacade empleadoFacade;

    public EmpleadoController(EmpleadoFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("/getPorSucursal/{id}")
    public ResponseEntity<?> getPorSucursal(@PathVariable Long id, @RequestParam(required = false) Integer limit, @RequestParam(required = false) Long startId, @RequestParam(required = false) String searchString) {
        List<EmpleadoDto> empleados = empleadoFacade.getPorSucursal(id, searchString);
        if (startId != null) {
            int startIndex = (startId.intValue() - 1) * limit;
            int endIndex = Math.min(startIndex + limit, empleados.size());
            if (startIndex < empleados.size()) {
                empleados = empleados.subList(startIndex, endIndex);
            } else {
                empleados = new ArrayList<>();
            }
        }
        return ResponseEntity.ok(empleados);
    }

    @PostMapping("/getPorMail")
    public ResponseEntity<?> getPorMail(@RequestBody String mail) {
        List<EmpleadoDto> empleados = facade.getAll();
        for (EmpleadoDto empleado :
                empleados) {
            if (empleado.getEmail().equals(mail)) {
                return ResponseEntity.ok(empleado);
            }
        }
        return ResponseEntity.ok("No se encontro un empleado con ese mail");
    }

    @PutMapping(value = "/save/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> edit(@RequestPart("entity") EmpleadoCreateDto entity, @RequestPart("file") MultipartFile file, @PathVariable Long id) {
        try {
            System.out.println("Estoy en controller");
            EmpleadoDto empleado = facade.update(entity, id);
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
    @GetMapping("/getEmpleados")
    public ResponseEntity<List<EmpleadoDto>> getDeliverys(){
        return ResponseEntity.ok(facade.getDeliverys());
    }

    @GetMapping("/role")
    public Map<String, Object> getUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst()
                .orElse("No Role");
        return Map.of("role", role);
    }

}
