package com.example.buensaborback.domain.dto.Articulo;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Categoria.CategoriaDto;
import com.example.buensaborback.domain.dto.ImageModel;
import com.example.buensaborback.domain.dto.UnidadMedida.UnidadMedidaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloDto extends BaseDto {
    private String denominacion;
    private Double precioVenta;
    //private List<String> imagenes;
    private UnidadMedidaDto unidadMedida;
    private CategoriaDto categoria;
}
