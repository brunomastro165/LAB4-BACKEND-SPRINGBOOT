package com.example.buensaborback.domain.dto.Localidad;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Provincia.ProvinciaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocalidadDto extends BaseDto {

    private String nombre;
    private ProvinciaDto provincia;
}
