package com.example.buensaborback.domain.dto.DetallePedido;

import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoDto extends BaseDto {
    private Integer cantidad;
    private Double subTotal;
    private ArticuloInsumoDto articuloInsumo;
    private ArticuloManufacturadoDto articuloManufacturado;
    private PromocionDto promocion;
}
