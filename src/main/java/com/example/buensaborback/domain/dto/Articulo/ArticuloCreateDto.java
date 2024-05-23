package com.example.buensaborback.domain.dto.Articulo;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.ImageModel;
import com.example.buensaborback.domain.dto.UnidadMedida.UnidadMedidaCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloCreateDto extends BaseDto {
    private String denominacion;
    private Double precioVenta;
    //private List<String> imagenes;
    private UnidadMedidaCreateDto UnidadMedida;
    private Long idCategoria;
}
