package com.example.buensaborback.domain.dto.Empresa;

import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaCreateDto extends BaseDto {
    private String nombre;
    private String razonSocial;
    private Long cuil;
}
