package com.example.buensaborback.business.services.base;

import com.example.buensaborback.domain.entities.Base;
import org.mapstruct.Named;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E extends Base, ID extends Serializable> {
    E create(E request);

    List<E> getAll();

    void deleteById(ID id);

    E update(E request, ID id);

    @Named("getById")
// Esta notacion califica al metodo para luego se utilizado en clase mappper
    E getById(ID id);
}
