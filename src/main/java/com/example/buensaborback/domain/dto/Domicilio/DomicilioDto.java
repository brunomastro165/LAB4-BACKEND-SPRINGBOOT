package com.example.buensaborback.domain.dto.Domicilio;


import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.Localidad.LocalidadDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DomicilioDto extends BaseDto {

    private String calle;
    private Integer numero;
    private Integer cp;
    private Integer piso;
    private Integer nroDpto;
    private LocalidadDto localidad;

}
