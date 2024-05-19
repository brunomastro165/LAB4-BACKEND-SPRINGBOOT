package com.example.buensaborback.domain.dto.Categoria;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalShortDto;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDto extends BaseDto {
    private String denominacion;
    private boolean esInsumo;
    private Set<SucursalShortDto> sucursales = new HashSet<>();
    private Set<Articulo> articulos = new HashSet<>();
    private Set<Categoria> subCategorias = new HashSet<>();
}
