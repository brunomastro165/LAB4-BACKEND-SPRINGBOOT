package com.example.buensaborback.domain.dtos;

import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Sucursal;
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
public class CategoriaDTO extends BaseDTO {

    private String denominacion;
    private Set<Sucursal> sucursales = new HashSet<>();
    private Set<Articulo> articulos = new HashSet<>();
    private Set<Categoria> subCategorias = new HashSet<>();
}