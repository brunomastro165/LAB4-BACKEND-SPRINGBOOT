package com.example.buensaborback.domain.dto.Sucursal;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioDto;
import com.example.buensaborback.domain.dto.Empresa.EmpresaDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SucursalDto extends BaseDto {

    private String nombre;
    @Schema(type = "string", format = "time", pattern = "HH:mm:ss", description = "Horario de apertura en formato HH:mm:ss")
    private LocalTime horarioApertura;
    @Schema(type = "string", format = "time", pattern = "HH:mm:ss", description = "Horario de apertura en formato HH:mm:ss")
    private LocalTime horarioCierre;
    private Boolean esCasaMatriz;
    private DomicilioDto domicilio;
    private EmpresaDto empresa;
    private Set<CategoriaDto> categorias;
}