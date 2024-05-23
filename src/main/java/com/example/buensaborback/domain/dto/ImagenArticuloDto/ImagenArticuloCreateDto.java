package com.example.buensaborback.domain.dto.ImagenArticuloDto;

import com.example.buensaborback.domain.dto.ImageModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImagenArticuloCreateDto extends ImageModel {
    private String url;
}
