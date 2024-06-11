package com.example.buensaborback.domain.dto.UsuarioEmpleado;

import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEmpleadoCreateDto extends BaseDto {

    private String auth0Id;
    private String userName;
}
