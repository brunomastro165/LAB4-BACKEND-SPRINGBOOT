package com.example.buensaborback.domain.dto.Categoria;

import com.example.buensaborback.domain.dto.Articulo.ArticuloCreateDto;
import com.example.buensaborback.domain.dto.Articulo.ArticuloDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalCreateDto;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
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

public class CategoriaCreateDto extends BaseDto {
    private String denominacion;
    private boolean esInsumo;
    private Set<SucursalCreateDto> sucursales = new HashSet<>();
    private Set<CategoriaCreateDto> subCategorias;
}
