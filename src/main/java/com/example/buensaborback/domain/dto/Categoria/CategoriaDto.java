package com.example.buensaborback.domain.dto.Categoria;

import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Insumo.ArticuloInsumoCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDto extends BaseDto {
    private String denominacion;
    private boolean esInsumo;
    private Set<ArticuloManufacturadoCreateDto> articulosManufacturados;
    private Set<ArticuloInsumoCreateDto> articulosInsumos;
    private Set<CategoriaShortDto> subCategorias;
}
