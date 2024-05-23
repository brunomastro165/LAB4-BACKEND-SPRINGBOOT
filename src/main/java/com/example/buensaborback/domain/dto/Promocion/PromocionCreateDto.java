package com.example.buensaborback.domain.dto.Promocion;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class PromocionCreateDto extends BaseDto {
    private String denominacion;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private LocalTime horaDesde;
    private LocalTime horaHasta;
    private Double precioPromocional;
    private SucursalDto sucursalDto;
}
