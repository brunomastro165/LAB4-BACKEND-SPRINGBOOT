package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.EmpresaFacadeImpl;
import com.example.buensaborback.domain.dtos.EmpresaDTO;
import com.example.buensaborback.domain.dtos.shortDTO.EmpresaShortDTO;
import com.example.buensaborback.domain.dtos.shortDTO.SucursalShortDTO;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/empresa")
public class EmpresaController extends BaseControllerImpl<Empresa, EmpresaDTO, Long, EmpresaFacadeImpl> {
    public EmpresaController(EmpresaFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("/{id}/sucursales")
    public ResponseEntity<Set<SucursalShortDTO>> getSucursalesByEmpresaId(@PathVariable Long id) {
        try {
            return facade.getSucursalesByEmpresaId(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}/short")
    public ResponseEntity<EmpresaShortDTO> getShort(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(facade.getShort(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/short")
    public ResponseEntity<EmpresaShortDTO> createShort(@RequestBody EmpresaShortDTO empresaShortDTO) {
        try {
            return ResponseEntity.ok(facade.saveShort(empresaShortDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}/short")
    public ResponseEntity<Void> deleteShort(@PathVariable Long id) {
        try {
            facade.deleteShort(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/short")
    public ResponseEntity<List<EmpresaShortDTO>> getAllShort() {
        try {
            return ResponseEntity.ok(facade.findAllShort());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/short")
    public ResponseEntity<EmpresaShortDTO> updateShort(@PathVariable Long id, @RequestBody EmpresaShortDTO empresaShortDTO) {
        try {
            return ResponseEntity.ok(facade.updateShort(id, empresaShortDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

