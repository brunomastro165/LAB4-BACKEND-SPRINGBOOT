package com.example.buensaborback.domain.dto.UnidadMedida;

import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UnidadMedidaCreateDto extends BaseDto {
    private String denominacion;
}
