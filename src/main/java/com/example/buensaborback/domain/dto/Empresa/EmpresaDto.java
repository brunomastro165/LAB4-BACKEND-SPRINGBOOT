package com.example.buensaborback.domain.dto.Empresa;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.ImagenEmpresa.ImagenEmpresaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpresaDto extends BaseDto {

    private String nombre;
    private String razonSocial;
    private Long cuil;
    private List<ImagenEmpresaDto> imagenes;

}

