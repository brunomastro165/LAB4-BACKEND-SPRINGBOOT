package com.example.buensaborback.domain.dto.Categoria;

import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaShortDto extends BaseDto {
    private String denominacion;
    private boolean esInsumo;
    private Set<CategoriaDto> subCategorias;
}
