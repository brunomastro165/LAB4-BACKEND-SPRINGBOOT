package com.example.buensaborback.domain.dto.Categoria;

import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalShortDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDto extends BaseDto {
    private String denominacion;

    private boolean esInsumo;

    private boolean esPadre;

    private Set<SucursalShortDto> sucursales;

    private Set<CategoriaShortDto> subCategorias;

    private Set<ArticuloInsumoDto> insumos;

    private Set<ArticuloManufacturadoDto> articulosManufacturados;

}
