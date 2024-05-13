package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dtos.PromocionDTO;
import com.example.buensaborback.domain.entities.Promocion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PromocionMapper extends BaseMapper<Promocion, PromocionDTO> {
    PromocionDTO toDTO(Promocion source);

    Promocion toEntity(PromocionDTO source);

    List<PromocionDTO> toDTOsList(List<Promocion> source);

    List<Promocion> toEntitiesList(List<PromocionDTO> source);
}
