package com.example.buensaborback.presentation.base;

import com.example.buensaborback.domain.dto.BaseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

public interface BaseController<D extends BaseDto, DC, DE, ID extends Serializable> {
    ResponseEntity<D> getById(ID id);

    ResponseEntity<List<D>> getAll(Integer limit, Long startId);

    ResponseEntity<D> create(DC entity);

    ResponseEntity<D> edit(DE entity, ID id);

    ResponseEntity<?> deleteById(ID id);

    ResponseEntity<?> activateById(@PathVariable ID id);
}
