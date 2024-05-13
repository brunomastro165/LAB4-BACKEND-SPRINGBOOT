package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dtos.ImagenPromocionDTO;
import com.example.buensaborback.domain.entities.ImagenPromocion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImagenPromocionMapper extends BaseMapper<ImagenPromocion, ImagenPromocionDTO> {
    ImagenPromocionDTO toDTO(ImagenPromocion source);

    ImagenPromocion toEntity(ImagenPromocionDTO source);

    List<ImagenPromocionDTO> toDTOsList(List<ImagenPromocion> source);

    List<ImagenPromocion> toEntitiesList(List<ImagenPromocionDTO> source);
}
