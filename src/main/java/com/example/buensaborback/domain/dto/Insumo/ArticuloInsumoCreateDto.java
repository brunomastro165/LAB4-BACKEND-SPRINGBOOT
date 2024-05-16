package com.example.buensaborback.domain.dto.Insumo;


import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.entities.UnidadMedida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloInsumoCreateDto extends BaseDto {
    private String denominacion;
    private Double precioVenta;
    private UnidadMedida UnidadMedida;
    private Double precioCompra;
    private Integer stockActual;
    private Integer stockMaximo;
    private Boolean esParaElaborar;
}
