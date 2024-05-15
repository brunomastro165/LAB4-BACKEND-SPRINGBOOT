package com.example.buensaborback.domain.dto.Provincia;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Pais.PaisDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProvinciaDto extends BaseDto {

    private String nombre;
    private PaisDto pais;

}
