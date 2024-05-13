package com.example.buensaborback.business.facade.base;

import com.example.buensaborback.domain.dtos.BaseDTO;

import java.io.Serializable;
import java.util.List;

public interface BaseFacade<D extends BaseDTO, ID extends Serializable> {
    public List<D> findAll() throws Exception;

    public D findById(ID id) throws Exception;

    public D save(D request) throws Exception;

    public D update(ID id, D request) throws Exception;

    public boolean delete(ID id) throws Exception;
}