package com.example.buensaborback.domain.dto.Articulo;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.ImagenArticuloDto.ImagenArticuloDto;
import com.example.buensaborback.domain.dto.UnidadMedida.UnidadMedidaCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloCreateDto extends BaseDto {
    private String denominacion;
    private Double precioVenta;
    private UnidadMedidaCreateDto UnidadMedida;
    private Long idCategoria;

    private List<ImagenArticuloDto> imagenes;
}
