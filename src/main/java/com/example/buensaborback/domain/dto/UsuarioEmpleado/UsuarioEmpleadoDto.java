package com.example.buensaborback.domain.dto.UsuarioEmpleado;

import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEmpleadoDto extends BaseDto {
    private Long id;
    private String userName;
}
