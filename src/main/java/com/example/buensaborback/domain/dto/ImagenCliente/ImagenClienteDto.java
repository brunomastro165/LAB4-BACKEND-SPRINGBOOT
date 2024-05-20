package com.example.buensaborback.domain.dto.ImagenCliente;

import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImagenClienteDto extends BaseDto {
    private String url;
}
