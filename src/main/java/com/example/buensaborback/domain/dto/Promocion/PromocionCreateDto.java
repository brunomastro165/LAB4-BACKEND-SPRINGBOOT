package com.example.buensaborback.domain.dto.Promocion;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.ImagenPromocion.ImagenPromocionDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleCreateDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalDto;
import com.example.buensaborback.domain.entities.ImagenPromocion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromocionCreateDto extends BaseDto {
    private String denominacion;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private LocalTime horaDesde;
    private LocalTime horaHasta;
    private Double precioPromocional;
    private Set<Long> idSucursales;
    private Set<PromocionDetalleCreateDto> detalles;
}
