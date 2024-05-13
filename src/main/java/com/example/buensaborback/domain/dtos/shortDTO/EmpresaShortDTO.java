package com.example.buensaborback.domain.dtos.shortDTO;

import com.example.buensaborback.domain.dtos.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpresaShortDTO extends BaseDTO {

    private String nombre;
    private String razonSocial;
    private Long cuil;
}
