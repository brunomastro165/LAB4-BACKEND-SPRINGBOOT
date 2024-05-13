package com.example.buensaborback.domain.dtos;

import com.example.buensaborback.domain.entities.ImagenArticulo;
import com.example.buensaborback.domain.entities.UnidadMedida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticuloDTO extends BaseDTO {

    protected String denominacion;
    protected Double precioVenta;
    protected Set<ImagenArticulo> imagenes = new HashSet<>();
    protected UnidadMedida unidadMedida;
}
