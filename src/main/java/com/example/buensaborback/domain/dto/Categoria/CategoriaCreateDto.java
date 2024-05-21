package com.example.buensaborback.domain.dto.Categoria;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaCreateDto {
    private String denominacion;

    private Set<Long> idSucursales;

    private boolean esInsumo;


}