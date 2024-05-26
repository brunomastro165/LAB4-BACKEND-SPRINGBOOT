package com.example.buensaborback.domain.dto.Articulo;

import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloShortDto extends BaseDto {
    private String denominacion;
    private Double precioVenta;
}
