package com.example.buensaborback.business.facade.base;

import com.example.buensaborback.domain.dto.BaseDto;

import java.io.Serializable;
import java.util.List;

public interface BaseFacade<D extends BaseDto, DC, DE, ID extends Serializable> {
    D createNew(DC request);

    D getById(Long id);

    List<D> getAll();

    void deleteById(Long id);

    D update(DE request, Long id);
}
