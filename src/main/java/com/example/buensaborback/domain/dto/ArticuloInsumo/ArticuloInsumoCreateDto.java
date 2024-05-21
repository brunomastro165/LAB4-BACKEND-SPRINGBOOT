package com.example.buensaborback.domain.dto.ArticuloInsumo;


import com.example.buensaborback.domain.dto.Articulo.ArticuloCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloInsumoCreateDto extends ArticuloCreateDto {
    private Double precioCompra;
    private Integer stockActual;
    private Integer stockMinimo;
    private Integer stockMaximo;
    private Boolean esParaElaborar;

}
