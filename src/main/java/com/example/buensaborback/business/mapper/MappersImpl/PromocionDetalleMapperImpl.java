package com.example.buensaborback.business.mapper.MappersImpl;

import com.example.buensaborback.business.mapper.ArticuloInsumoMapper;
import com.example.buensaborback.business.mapper.ArticuloManufacturadoMapper;
import com.example.buensaborback.business.mapper.PromocionDetalleMapper;
import com.example.buensaborback.business.services.ArticuloInsumoService;
import com.example.buensaborback.business.services.ArticuloManufacturadoService;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleCreateDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

import com.example.buensaborback.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PromocionDetalleMapperImpl implements PromocionDetalleMapper {
    @Autowired
    ArticuloInsumoService articuloInsumoService;
    @Autowired
    ArticuloRepository articuloRepository;
    @Autowired
    ArticuloManufacturadoService articuloManufacturadoService;
    @Autowired
    ArticuloManufacturadoMapper articuloManufacturadoMapper;
    @Autowired
    ArticuloInsumoMapper articuloInsumoMapper;

    @Override
    public PromocionDetalleDto toDTO(PromocionDetalle source) {
        if ( source == null ) {
            return null;
        }

        Integer cantidad = null;

        cantidad = source.getCantidad();

        ArticuloInsumoDto insumos = null;
        ArticuloManufacturadoDto articulosManufacturados = null;

        PromocionDetalleDto promocionDetalleDto = new PromocionDetalleDto( cantidad, insumos, articulosManufacturados );

        promocionDetalleDto.setId( source.getId() );
        if ( source.isEliminado() != null ) {
            promocionDetalleDto.setEliminado( source.isEliminado() );
        }
        if(source.getArticulo() instanceof ArticuloInsumo){
            promocionDetalleDto.setInsumos(articuloInsumoMapper.toDTO(articuloInsumoService.getById(source.getArticulo().getId())));
        }

        if(source.getArticulo() instanceof ArticuloManufacturado){
            promocionDetalleDto.setArticulosManufacturados(articuloManufacturadoMapper.toDTO(articuloManufacturadoService.getById(source.getArticulo().getId())));
        }

        return promocionDetalleDto;
    }

    @Override
    public PromocionDetalle toEntity(PromocionDetalleDto source) {
        if ( source == null ) {
            return null;
        }

        PromocionDetalle.PromocionDetalleBuilder<?, ?> promocionDetalle = PromocionDetalle.builder();

        promocionDetalle.id( source.getId() );
        promocionDetalle.eliminado( source.isEliminado() );
        promocionDetalle.cantidad( source.getCantidad() );
        if(source.getInsumos() != null)
            promocionDetalle.articulo(articuloInsumoService.getById(source.getInsumos().getId()));
        if(source.getArticulosManufacturados() != null)
            promocionDetalle.articulo(articuloManufacturadoService.getById(source.getArticulosManufacturados().getId()));

        return promocionDetalle.build();
    }

    @Override
    public PromocionDetalle toEntityCreate(PromocionDetalleCreateDto source) {
        if ( source == null ) {
            return null;
        }

        PromocionDetalle.PromocionDetalleBuilder<?, ?> promocionDetalle = PromocionDetalle.builder();

        promocionDetalle.id( source.getId() );
        promocionDetalle.eliminado( source.isEliminado() );
        promocionDetalle.cantidad( source.getCantidad() );
        if(articuloRepository.getById(source.getIdArticulo()) instanceof ArticuloInsumo)
            promocionDetalle.articulo(articuloInsumoService.getById(source.getIdArticulo()));
        if(articuloRepository.getById(source.getIdArticulo()) instanceof ArticuloManufacturado)
            promocionDetalle.articulo(articuloManufacturadoService.getById(source.getIdArticulo()));

        return promocionDetalle.build();
    }

    @Override
    public PromocionDetalle toUpdate(PromocionDetalle entity, PromocionDetalleCreateDto source) {
        if ( source == null ) {
            return entity;
        }

        entity.setId( source.getId() );
        entity.setEliminado( source.isEliminado() );
        entity.setCantidad( source.getCantidad() );
        if(articuloRepository.getById(source.getIdArticulo()) instanceof ArticuloInsumo)
            entity.setArticulo(articuloInsumoService.getById(source.getIdArticulo()));
        if(articuloRepository.getById(source.getIdArticulo()) instanceof ArticuloManufacturado)
            entity.setArticulo(articuloManufacturadoService.getById(source.getIdArticulo()));

        return entity;
    }

    @Override
    public List<PromocionDetalleDto> toDTOsList(List<PromocionDetalle> source) {
        if ( source == null ) {
            return null;
        }

        List<PromocionDetalleDto> list = new ArrayList<PromocionDetalleDto>( source.size() );
        for ( PromocionDetalle promocionDetalle : source ) {
            list.add( toDTO( promocionDetalle ) );
        }

        return list;
    }
}