package com.example.buensaborback.domain.dto.Articulo;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.ImageModel;
import com.example.buensaborback.domain.dto.UnidadMedida.UnidadMedidaCreateDto;
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
public class ArticuloCreateDto extends BaseDto {
    private String denominacion;
    private Double precioVenta;
    //private Set<ImageModel> imagenes = new HashSet<>();
    private UnidadMedidaCreateDto UnidadMedida;
    private Long idCategoria;
}
