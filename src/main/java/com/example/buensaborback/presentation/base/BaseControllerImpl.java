package com.example.buensaborback.presentation.base;

import com.example.buensaborback.business.facade.base.BaseFacadeImpl;
import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.entities.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Transactional
public abstract class BaseControllerImpl<E extends Base, D extends BaseDto, DC, DE, ID extends Serializable, F extends BaseFacadeImpl<E, D, DC, DE, ID>> implements BaseController<D, DC, DE, ID> {

    private static final Logger logger = LoggerFactory.getLogger(BaseControllerImpl.class);
    protected F facade;

    public BaseControllerImpl(F facade) {
        this.facade = facade;
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> getById(@PathVariable ID id) {
        logger.info("INICIO GET BY ID {}", id);
        return ResponseEntity.ok(facade.getById(id));
    }

    @GetMapping()
    public ResponseEntity<List<D>> getAll(@RequestParam(required = false) Integer limit, @RequestParam(required = false) Long startId) {
        logger.info("INICIO GET ALL");
        List<D> allItems = facade.getAll();

        if (startId != null) {
            int startIndex = (startId.intValue() - 1) * limit;
            int endIndex = Math.min(startIndex + limit, allItems.size());
            if (startIndex < allItems.size()) {
                allItems = allItems.subList(startIndex, endIndex);
            } else {
                allItems = new ArrayList<>();
            }
        }

        return ResponseEntity.ok(allItems);
    }


    @GetMapping("/eliminados")
    public ResponseEntity<List<D>> getEliminados() {
        logger.info("INICIO GET ELIMINADOS");
        List<D> allElements = facade.getAll();
        List<D> filteredElements = allElements.stream()
                .filter(a -> a.isEliminado())
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredElements);
    }

    @GetMapping("/noEliminados")
    public ResponseEntity<List<D>> getNoEliminados() {
        logger.info("INICIO GET NO ELIMINADOS");
        List<D> allElements = facade.getAll();
        List<D> filteredElements = allElements.stream()
                .filter(a -> !a.isEliminado())
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredElements);
    }


    @PostMapping()
    public ResponseEntity<D> create(@RequestBody DC entity) {
        try {
            System.out.println("Estoy en controller");
            logger.info("INICIO CREATE {}", entity.getClass());
            return ResponseEntity.ok(facade.createNew(entity));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> edit(@RequestBody DE entity, @PathVariable ID id) {

        logger.info("INICIO EDIT {}", entity.getClass());
        return ResponseEntity.ok(facade.update(entity, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ID id) {
        logger.info("INICIO DELETE BY ID");
        facade.deleteById(id);
        return ResponseEntity.ok("La entidad fue borrada con exito");
    }

    @PostMapping("/activate/{id}")
    public ResponseEntity<?> activateById(@PathVariable ID id) {
        logger.info("INICIO DELETE BY ID");
        facade.activateById(id);
        return ResponseEntity.ok("La entidad fue activada con exito");
    }
}