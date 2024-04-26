package com.example.buensaborback.services;

import com.example.buensaborback.entities.Base;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<E extends Base, ID extends Serializable> {
    E create(E user);
    E getById(ID id);
    List<E> getAll();
    void deleteById(ID id);
    E update(E user);
}
