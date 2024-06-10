package com.example.buensaborback.business.mapper.MappersImpl;

import com.example.buensaborback.business.mapper.ArticuloInsumoMapper;
import com.example.buensaborback.business.mapper.ArticuloManufacturadoMapper;
import com.example.buensaborback.business.mapper.DetallePedidoMapper;
import com.example.buensaborback.business.services.ArticuloInsumoService;
import com.example.buensaborback.business.services.ArticuloManufacturadoService;
import com.example.buensaborback.business.services.PromocionService;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoCreateDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioDto;
import com.example.buensaborback.domain.dto.ImagenPromocion.ImagenPromocionDto;
import com.example.buensaborback.domain.dto.ImagenSucursal.ImagenSucursalDto;
import com.example.buensaborback.domain.dto.Localidad.LocalidadDto;
import com.example.buensaborback.domain.dto.Pais.PaisDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.example.buensaborback.domain.dto.Provincia.ProvinciaDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalShortDto;
import com.example.buensaborback.domain.entities.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;

import com.example.buensaborback.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
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

    @Autowired
    private PromocionService promocionService;

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
        detallePedidoDto.setPromocion( promocionToPromocionDto( source.getPromocion() ) );
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
        detallePedido.promocion( promocionDtoToPromocion( source.getPromocion() ) );
        if(source.getArticuloInsumo() != null)
            detallePedido.articulo(articuloInsumoService.getById(source.getArticuloInsumo().getId()));
        if(source.getArticuloManufacturado() != null)
            detallePedido.articulo(articuloManufacturadoService.getById(source.getArticuloManufacturado().getId()));


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
        if(source.getIdPromocion() !=null)
        entity.setPromocion(promocionService.getById(source.getIdPromocion()));
        else {
            if(articuloRepository.getById(source.getIdArticulo()) instanceof ArticuloInsumo)
                entity.setArticulo(articuloInsumoService.getById(source.getIdArticulo()));
            if(articuloRepository.getById(source.getIdArticulo()) instanceof ArticuloManufacturado)
                entity.setArticulo(articuloManufacturadoService.getById(source.getIdArticulo()));
        }

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

    @Override
    public DetallePedido toEntityCreate(DetallePedidoCreateDto source) {
        if ( source == null ) {
            return null;
        }

        DetallePedido.DetallePedidoBuilder<?, ?> detallePedido = DetallePedido.builder();
        if(source.getIdPromocion() != null)
        detallePedido.promocion( promocionService.getById( source.getIdPromocion() ) );
        else{
            if(articuloRepository.getById(source.getIdArticulo()) instanceof ArticuloInsumo)
                detallePedido.articulo(articuloInsumoService.getById(source.getIdArticulo()));
            if(articuloRepository.getById(source.getIdArticulo()) instanceof ArticuloManufacturado)
                detallePedido.articulo(articuloManufacturadoService.getById(source.getIdArticulo()));
        }
        detallePedido.id( source.getId() );
        detallePedido.eliminado( source.isEliminado() );
        detallePedido.cantidad( source.getCantidad() );
        detallePedido.subTotal( source.getSubTotal() );

        return detallePedido.build();
    }

    protected ImagenPromocionDto imagenPromocionToImagenPromocionDto(ImagenPromocion imagenPromocion) {
        if ( imagenPromocion == null ) {
            return null;
        }

        ImagenPromocionDto imagenPromocionDto = new ImagenPromocionDto();

        imagenPromocionDto.setName( imagenPromocion.getName() );
        imagenPromocionDto.setUrl( imagenPromocion.getUrl() );

        return imagenPromocionDto;
    }

    protected List<ImagenPromocionDto> imagenPromocionSetToImagenPromocionDtoList(Set<ImagenPromocion> set) {
        if ( set == null ) {
            return null;
        }

        List<ImagenPromocionDto> list = new ArrayList<ImagenPromocionDto>( set.size() );
        for ( ImagenPromocion imagenPromocion : set ) {
            list.add( imagenPromocionToImagenPromocionDto( imagenPromocion ) );
        }

        return list;
    }

    protected PaisDto paisToPaisDto(Pais pais) {
        if ( pais == null ) {
            return null;
        }

        PaisDto paisDto = new PaisDto();

        if ( pais.isEliminado() != null ) {
            paisDto.setEliminado( pais.isEliminado() );
        }
        paisDto.setId( pais.getId() );
        paisDto.setNombre( pais.getNombre() );

        return paisDto;
    }

    protected ProvinciaDto provinciaToProvinciaDto(Provincia provincia) {
        if ( provincia == null ) {
            return null;
        }

        ProvinciaDto provinciaDto = new ProvinciaDto();

        provinciaDto.setId( provincia.getId() );
        if ( provincia.isEliminado() != null ) {
            provinciaDto.setEliminado( provincia.isEliminado() );
        }
        provinciaDto.setNombre( provincia.getNombre() );
        provinciaDto.setPais( paisToPaisDto( provincia.getPais() ) );

        return provinciaDto;
    }

    protected LocalidadDto localidadToLocalidadDto(Localidad localidad) {
        if ( localidad == null ) {
            return null;
        }

        LocalidadDto localidadDto = new LocalidadDto();

        localidadDto.setId( localidad.getId() );
        if ( localidad.isEliminado() != null ) {
            localidadDto.setEliminado( localidad.isEliminado() );
        }
        localidadDto.setNombre( localidad.getNombre() );
        localidadDto.setProvincia( provinciaToProvinciaDto( localidad.getProvincia() ) );

        return localidadDto;
    }

    protected DomicilioDto domicilioToDomicilioDto(Domicilio domicilio) {
        if ( domicilio == null ) {
            return null;
        }

        DomicilioDto domicilioDto = new DomicilioDto();

        domicilioDto.setId( domicilio.getId() );
        if ( domicilio.isEliminado() != null ) {
            domicilioDto.setEliminado( domicilio.isEliminado() );
        }
        domicilioDto.setCalle( domicilio.getCalle() );
        domicilioDto.setNumero( domicilio.getNumero() );
        domicilioDto.setCp( domicilio.getCp() );
        domicilioDto.setPiso( domicilio.getPiso() );
        domicilioDto.setNroDpto( domicilio.getNroDpto() );
        domicilioDto.setLocalidad( localidadToLocalidadDto( domicilio.getLocalidad() ) );

        return domicilioDto;
    }

    protected ImagenSucursalDto imagenSucursalToImagenSucursalDto(ImagenSucursal imagenSucursal) {
        if ( imagenSucursal == null ) {
            return null;
        }

        ImagenSucursalDto imagenSucursalDto = new ImagenSucursalDto();

        imagenSucursalDto.setName( imagenSucursal.getName() );
        imagenSucursalDto.setUrl( imagenSucursal.getUrl() );

        return imagenSucursalDto;
    }

    protected List<ImagenSucursalDto> imagenSucursalSetToImagenSucursalDtoList(Set<ImagenSucursal> set) {
        if ( set == null ) {
            return null;
        }

        List<ImagenSucursalDto> list = new ArrayList<ImagenSucursalDto>( set.size() );
        for ( ImagenSucursal imagenSucursal : set ) {
            list.add( imagenSucursalToImagenSucursalDto( imagenSucursal ) );
        }

        return list;
    }

    protected SucursalShortDto sucursalToSucursalShortDto(Sucursal sucursal) {
        if ( sucursal == null ) {
            return null;
        }

        SucursalShortDto sucursalShortDto = new SucursalShortDto();

        sucursalShortDto.setId( sucursal.getId() );
        if ( sucursal.isEliminado() != null ) {
            sucursalShortDto.setEliminado( sucursal.isEliminado() );
        }
        sucursalShortDto.setNombre( sucursal.getNombre() );
        sucursalShortDto.setHorarioApertura( sucursal.getHorarioApertura() );
        sucursalShortDto.setHorarioCierre( sucursal.getHorarioCierre() );
        sucursalShortDto.setEsCasaMatriz( sucursal.getEsCasaMatriz() );
        sucursalShortDto.setDomicilio( domicilioToDomicilioDto( sucursal.getDomicilio() ) );
        sucursalShortDto.setImagenes( imagenSucursalSetToImagenSucursalDtoList( sucursal.getImagenes() ) );

        return sucursalShortDto;
    }

    protected Set<SucursalShortDto> sucursalSetToSucursalShortDtoSet(Set<Sucursal> set) {
        if ( set == null ) {
            return null;
        }

        Set<SucursalShortDto> set1 = new LinkedHashSet<SucursalShortDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Sucursal sucursal : set ) {
            set1.add( sucursalToSucursalShortDto( sucursal ) );
        }

        return set1;
    }

    protected PromocionDetalleDto promocionDetalleToPromocionDetalleDto(PromocionDetalle promocionDetalle) {
        if ( promocionDetalle == null ) {
            return null;
        }

        Integer cantidad = null;

        cantidad = promocionDetalle.getCantidad();

        ArticuloInsumoDto insumos = null;
        ArticuloManufacturadoDto articulosManufacturados = null;

        PromocionDetalleDto promocionDetalleDto = new PromocionDetalleDto( cantidad, insumos, articulosManufacturados );

        promocionDetalleDto.setId( promocionDetalle.getId() );
        if ( promocionDetalle.isEliminado() != null ) {
            promocionDetalleDto.setEliminado( promocionDetalle.isEliminado() );
        }

        return promocionDetalleDto;
    }

    protected Set<PromocionDetalleDto> promocionDetalleSetToPromocionDetalleDtoSet(Set<PromocionDetalle> set) {
        if ( set == null ) {
            return null;
        }

        Set<PromocionDetalleDto> set1 = new LinkedHashSet<PromocionDetalleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PromocionDetalle promocionDetalle : set ) {
            set1.add( promocionDetalleToPromocionDetalleDto( promocionDetalle ) );
        }

        return set1;
    }

    protected PromocionDto promocionToPromocionDto(Promocion promocion) {
        if ( promocion == null ) {
            return null;
        }

        PromocionDto promocionDto = new PromocionDto();

        promocionDto.setId( promocion.getId() );
        if ( promocion.isEliminado() != null ) {
            promocionDto.setEliminado( promocion.isEliminado() );
        }
        promocionDto.setDenominacion( promocion.getDenominacion() );
        promocionDto.setFechaDesde( promocion.getFechaDesde() );
        promocionDto.setFechaHasta( promocion.getFechaHasta() );
        promocionDto.setHoraDesde( promocion.getHoraDesde() );
        promocionDto.setHoraHasta( promocion.getHoraHasta() );
        promocionDto.setPrecioPromocional( promocion.getPrecioPromocional() );
        promocionDto.setImagenes( imagenPromocionSetToImagenPromocionDtoList( promocion.getImagenes() ) );
        promocionDto.setSucursales( sucursalSetToSucursalShortDtoSet( promocion.getSucursales() ) );
        promocionDto.setDetalles( promocionDetalleSetToPromocionDetalleDtoSet( promocion.getDetalles() ) );
        promocionDto.setDescripcionDescuento( promocion.getDescripcionDescuento() );
        promocionDto.setTipoPromocion( promocion.getTipoPromocion() );

        return promocionDto;
    }

    protected ImagenPromocion imagenPromocionDtoToImagenPromocion(ImagenPromocionDto imagenPromocionDto) {
        if ( imagenPromocionDto == null ) {
            return null;
        }

        ImagenPromocion.ImagenPromocionBuilder<?, ?> imagenPromocion = ImagenPromocion.builder();

        imagenPromocion.name( imagenPromocionDto.getName() );
        imagenPromocion.url( imagenPromocionDto.getUrl() );

        return imagenPromocion.build();
    }

    protected Set<ImagenPromocion> imagenPromocionDtoListToImagenPromocionSet(List<ImagenPromocionDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<ImagenPromocion> set = new LinkedHashSet<ImagenPromocion>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( ImagenPromocionDto imagenPromocionDto : list ) {
            set.add( imagenPromocionDtoToImagenPromocion( imagenPromocionDto ) );
        }

        return set;
    }

    protected Pais paisDtoToPais(PaisDto paisDto) {
        if ( paisDto == null ) {
            return null;
        }

        Pais.PaisBuilder<?, ?> pais = Pais.builder();

        pais.id( paisDto.getId() );
        pais.eliminado( paisDto.isEliminado() );
        pais.nombre( paisDto.getNombre() );

        return pais.build();
    }

    protected Provincia provinciaDtoToProvincia(ProvinciaDto provinciaDto) {
        if ( provinciaDto == null ) {
            return null;
        }

        Provincia.ProvinciaBuilder<?, ?> provincia = Provincia.builder();

        provincia.id( provinciaDto.getId() );
        provincia.eliminado( provinciaDto.isEliminado() );
        provincia.nombre( provinciaDto.getNombre() );
        provincia.pais( paisDtoToPais( provinciaDto.getPais() ) );

        return provincia.build();
    }

    protected Localidad localidadDtoToLocalidad(LocalidadDto localidadDto) {
        if ( localidadDto == null ) {
            return null;
        }

        Localidad.LocalidadBuilder<?, ?> localidad = Localidad.builder();

        localidad.id( localidadDto.getId() );
        localidad.eliminado( localidadDto.isEliminado() );
        localidad.nombre( localidadDto.getNombre() );
        localidad.provincia( provinciaDtoToProvincia( localidadDto.getProvincia() ) );

        return localidad.build();
    }

    protected Domicilio domicilioDtoToDomicilio(DomicilioDto domicilioDto) {
        if ( domicilioDto == null ) {
            return null;
        }

        Domicilio.DomicilioBuilder<?, ?> domicilio = Domicilio.builder();

        domicilio.id( domicilioDto.getId() );
        domicilio.eliminado( domicilioDto.isEliminado() );
        domicilio.calle( domicilioDto.getCalle() );
        domicilio.numero( domicilioDto.getNumero() );
        domicilio.cp( domicilioDto.getCp() );
        domicilio.piso( domicilioDto.getPiso() );
        domicilio.nroDpto( domicilioDto.getNroDpto() );
        domicilio.localidad( localidadDtoToLocalidad( domicilioDto.getLocalidad() ) );

        return domicilio.build();
    }

    protected ImagenSucursal imagenSucursalDtoToImagenSucursal(ImagenSucursalDto imagenSucursalDto) {
        if ( imagenSucursalDto == null ) {
            return null;
        }

        ImagenSucursal.ImagenSucursalBuilder<?, ?> imagenSucursal = ImagenSucursal.builder();

        imagenSucursal.name( imagenSucursalDto.getName() );
        imagenSucursal.url( imagenSucursalDto.getUrl() );

        return imagenSucursal.build();
    }

    protected Set<ImagenSucursal> imagenSucursalDtoListToImagenSucursalSet(List<ImagenSucursalDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<ImagenSucursal> set = new LinkedHashSet<ImagenSucursal>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( ImagenSucursalDto imagenSucursalDto : list ) {
            set.add( imagenSucursalDtoToImagenSucursal( imagenSucursalDto ) );
        }

        return set;
    }

    protected Sucursal sucursalShortDtoToSucursal(SucursalShortDto sucursalShortDto) {
        if ( sucursalShortDto == null ) {
            return null;
        }

        Sucursal.SucursalBuilder<?, ?> sucursal = Sucursal.builder();

        sucursal.id( sucursalShortDto.getId() );
        sucursal.eliminado( sucursalShortDto.isEliminado() );
        sucursal.nombre( sucursalShortDto.getNombre() );
        sucursal.horarioApertura( sucursalShortDto.getHorarioApertura() );
        sucursal.horarioCierre( sucursalShortDto.getHorarioCierre() );
        sucursal.esCasaMatriz( sucursalShortDto.getEsCasaMatriz() );
        sucursal.domicilio( domicilioDtoToDomicilio( sucursalShortDto.getDomicilio() ) );
        sucursal.imagenes( imagenSucursalDtoListToImagenSucursalSet( sucursalShortDto.getImagenes() ) );

        return sucursal.build();
    }

    protected Set<Sucursal> sucursalShortDtoSetToSucursalSet(Set<SucursalShortDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Sucursal> set1 = new LinkedHashSet<Sucursal>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( SucursalShortDto sucursalShortDto : set ) {
            set1.add( sucursalShortDtoToSucursal( sucursalShortDto ) );
        }

        return set1;
    }

    protected PromocionDetalle promocionDetalleDtoToPromocionDetalle(PromocionDetalleDto promocionDetalleDto) {
        if ( promocionDetalleDto == null ) {
            return null;
        }

        PromocionDetalle.PromocionDetalleBuilder<?, ?> promocionDetalle = PromocionDetalle.builder();

        promocionDetalle.id( promocionDetalleDto.getId() );
        promocionDetalle.eliminado( promocionDetalleDto.isEliminado() );
        promocionDetalle.cantidad( promocionDetalleDto.getCantidad() );

        return promocionDetalle.build();
    }

    protected Set<PromocionDetalle> promocionDetalleDtoSetToPromocionDetalleSet(Set<PromocionDetalleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<PromocionDetalle> set1 = new LinkedHashSet<PromocionDetalle>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PromocionDetalleDto promocionDetalleDto : set ) {
            set1.add( promocionDetalleDtoToPromocionDetalle( promocionDetalleDto ) );
        }

        return set1;
    }

    protected Promocion promocionDtoToPromocion(PromocionDto promocionDto) {
        if ( promocionDto == null ) {
            return null;
        }

        Promocion.PromocionBuilder<?, ?> promocion = Promocion.builder();

        promocion.id( promocionDto.getId() );
        promocion.eliminado( promocionDto.isEliminado() );
        promocion.denominacion( promocionDto.getDenominacion() );
        promocion.fechaDesde( promocionDto.getFechaDesde() );
        promocion.fechaHasta( promocionDto.getFechaHasta() );
        promocion.horaDesde( promocionDto.getHoraDesde() );
        promocion.horaHasta( promocionDto.getHoraHasta() );
        promocion.descripcionDescuento( promocionDto.getDescripcionDescuento() );
        promocion.precioPromocional( promocionDto.getPrecioPromocional() );
        promocion.tipoPromocion( promocionDto.getTipoPromocion() );
        promocion.imagenes( imagenPromocionDtoListToImagenPromocionSet( promocionDto.getImagenes() ) );
        promocion.sucursales( sucursalShortDtoSetToSucursalSet( promocionDto.getSucursales() ) );
        promocion.detalles( promocionDetalleDtoSetToPromocionDetalleSet( promocionDto.getDetalles() ) );

        return promocion.build();
    }
}
*/