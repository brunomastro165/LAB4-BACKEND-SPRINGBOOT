package com.example.buensaborback.domain.dto.ImagenCliente;

import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImagenClienteCreateDto extends BaseDto {
    private String url;
}
