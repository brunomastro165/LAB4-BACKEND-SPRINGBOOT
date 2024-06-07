package com.example.buensaborback.business.mapper.MappersImpl;

import com.example.buensaborback.business.mapper.ArticuloInsumoMapper;
import com.example.buensaborback.business.mapper.ArticuloManufacturadoMapper;
import com.example.buensaborback.business.mapper.DetallePedidoMapper;
import com.example.buensaborback.business.services.ArticuloInsumoService;
import com.example.buensaborback.business.services.ArticuloManufacturadoService;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoCreateDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.DetallePedido;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

import com.example.buensaborback.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DetallePedidoMapperImpl implements DetallePedidoMapper {
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
    public DetallePedidoDto toDTO(DetallePedido source) {
        if ( source == null ) {
            return null;
        }
        DetallePedidoDto detallePedidoDto = new DetallePedidoDto();

        detallePedidoDto.setId( source.getId() );
        if ( source.isEliminado() != null ) {
            detallePedidoDto.setEliminado( source.isEliminado() );
        }
        detallePedidoDto.setCantidad( source.getCantidad() );
        detallePedidoDto.setSubTotal( source.getSubTotal() );
        if(source.getArticulo() instanceof ArticuloInsumo){
            detallePedidoDto.setArticuloInsumo(articuloInsumoMapper.toDTO(articuloInsumoService.getById(source.getArticulo().getId())));
        }

        if(source.getArticulo() instanceof ArticuloManufacturado){
            detallePedidoDto.setArticuloManufacturado(articuloManufacturadoMapper.toDTO(articuloManufacturadoService.getById(source.getArticulo().getId())));
        }


        return detallePedidoDto;
    }

    @Override
    public DetallePedido toEntity(DetallePedidoDto source) {
        if ( source == null ) {
            return null;
        }

        DetallePedido.DetallePedidoBuilder<?, ?> detallePedido = DetallePedido.builder();

        detallePedido.id( source.getId() );
        detallePedido.eliminado( source.isEliminado() );
        detallePedido.cantidad( source.getCantidad() );
        detallePedido.subTotal( source.getSubTotal() );

        return detallePedido.build();
    }

    @Override
    public DetallePedido toEntityCreate(DetallePedidoCreateDto source) {
        if ( source == null ) {
            return null;
        }

        DetallePedido.DetallePedidoBuilder<?, ?> detallePedido = DetallePedido.builder();

        detallePedido.id( source.getId() );
        detallePedido.eliminado( source.isEliminado() );
        detallePedido.cantidad( source.getCantidad() );
        detallePedido.subTotal( source.getSubTotal() );
        if(articuloRepository.getById(source.getIdArticulo()) instanceof ArticuloInsumo)
            detallePedido.articulo(articuloInsumoService.getById(source.getIdArticulo()));
        if(articuloRepository.getById(source.getIdArticulo()) instanceof ArticuloManufacturado)
            detallePedido.articulo(articuloManufacturadoService.getById(source.getIdArticulo()));

        return detallePedido.build();
    }

    @Override
    public DetallePedido toUpdate(DetallePedido entity, DetallePedidoCreateDto source) {
        if ( source == null ) {
            return entity;
        }

        entity.setId( source.getId() );
        entity.setEliminado( source.isEliminado() );
        entity.setCantidad( source.getCantidad() );
        entity.setSubTotal( source.getSubTotal() );

        return entity;
    }

    @Override
    public List<DetallePedidoDto> toDTOsList(List<DetallePedido> source) {
        if ( source == null ) {
            return null;
        }

        List<DetallePedidoDto> list = new ArrayList<DetallePedidoDto>( source.size() );
        for ( DetallePedido detallePedido : source ) {
            list.add( toDTO( detallePedido ) );
        }

        return list;
    }
}

