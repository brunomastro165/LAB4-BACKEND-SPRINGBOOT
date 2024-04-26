package com.example.buensaborback.services;

import com.example.buensaborback.entities.Localidad;

public interface ILocalidadService extends IBaseService<Localidad,Long>{
    Localidad asignarProvincia(Long id, Long idProvincia);
}
