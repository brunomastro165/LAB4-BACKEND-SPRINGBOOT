package com.example.buensaborback.business.mapper.MappersImpl;

import com.example.buensaborback.business.mapper.PromocionDetalleMapper;
import com.example.buensaborback.business.mapper.PromocionMapper;
import com.example.buensaborback.business.services.SucursalService;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioDto;
import com.example.buensaborback.domain.dto.ImagenPromocion.ImagenPromocionDto;
import com.example.buensaborback.domain.dto.ImagenSucursal.ImagenSucursalDto;
import com.example.buensaborback.domain.dto.Localidad.LocalidadDto;
import com.example.buensaborback.domain.dto.Pais.PaisDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionCreateDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionDto;
import com.example.buensaborback.domain.dto.Promocion.PromocionEditDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleCreateDto;
import com.example.buensaborback.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.example.buensaborback.domain.dto.Provincia.ProvinciaDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalShortDto;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.domain.entities.ImagenPromocion;
import com.example.buensaborback.domain.entities.ImagenSucursal;
import com.example.buensaborback.domain.entities.Localidad;
import com.example.buensaborback.domain.entities.Pais;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.domain.entities.Provincia;
import com.example.buensaborback.domain.entities.Sucursal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-06-08T21:57:40-0300",
        comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.4.1 (Eclipse Adoptium)"
)
@Component
public class PromocionMapperImpl implements PromocionMapper {

    @Autowired
    private SucursalService sucursalService;
    @Autowired
    private PromocionDetalleMapper promocionDetalleMapper;

    @Override
    public PromocionDto toDTO(Promocion source) {
        if ( source == null ) {
            return null;
        }

        PromocionDto promocionDto = new PromocionDto();

        promocionDto.setId( source.getId() );
        if ( source.isEliminado() != null ) {
            promocionDto.setEliminado( source.isEliminado() );
        }
        promocionDto.setDenominacion( source.getDenominacion() );
        promocionDto.setFechaDesde( source.getFechaDesde() );
        promocionDto.setFechaHasta( source.getFechaHasta() );
        promocionDto.setHoraDesde( source.getHoraDesde() );
        promocionDto.setHoraHasta( source.getHoraHasta() );
        promocionDto.setPrecioPromocional( source.getPrecioPromocional() );
        promocionDto.setImagenes( imagenPromocionSetToImagenPromocionDtoList( source.getImagenes() ) );
        promocionDto.setSucursales( sucursalSetToSucursalShortDtoSet( source.getSucursales() ) );
        promocionDto.setDetalles( promocionDetalleSetToPromocionDetalleDtoSet( source.getDetalles() ) );
        promocionDto.setDescripcionDescuento( source.getDescripcionDescuento() );
        promocionDto.setTipoPromocion( source.getTipoPromocion() );

        return promocionDto;
    }

    @Override
    public Promocion toEntity(PromocionDto source) {
        if ( source == null ) {
            return null;
        }

        Promocion.PromocionBuilder<?, ?> promocion = Promocion.builder();

        promocion.id( source.getId() );
        promocion.eliminado( source.isEliminado() );
        promocion.denominacion( source.getDenominacion() );
        promocion.fechaDesde( source.getFechaDesde() );
        promocion.fechaHasta( source.getFechaHasta() );
        promocion.horaDesde( source.getHoraDesde() );
        promocion.horaHasta( source.getHoraHasta() );
        promocion.descripcionDescuento( source.getDescripcionDescuento() );
        promocion.precioPromocional( source.getPrecioPromocional() );
        promocion.tipoPromocion( source.getTipoPromocion() );
        promocion.imagenes( imagenPromocionDtoListToImagenPromocionSet( source.getImagenes() ) );
        promocion.sucursales( sucursalShortDtoSetToSucursalSet( source.getSucursales() ) );
        promocion.detalles( promocionDetalleDtoSetToPromocionDetalleSet( source.getDetalles() ) );

        return promocion.build();
    }

    @Override
    public Promocion toUpdate(Promocion entity, PromocionEditDto source) {
        if ( source == null ) {
            return entity;
        }

        entity.setSucursales( longSetToSucursalSet( source.getIdSucursales() ) );
        entity.setId( source.getId() );
        entity.setEliminado( source.isEliminado() );
        entity.setDenominacion( source.getDenominacion() );
        entity.setFechaDesde( source.getFechaDesde() );
        entity.setFechaHasta( source.getFechaHasta() );
        entity.setHoraDesde( source.getHoraDesde() );
        entity.setHoraHasta( source.getHoraHasta() );
        entity.setDescripcionDescuento( source.getDescripcionDescuento() );
        entity.setPrecioPromocional( source.getPrecioPromocional() );
        entity.setTipoPromocion( source.getTipoPromocion() );
        if ( entity.getDetalles() != null ) {
            Set<PromocionDetalle> set = promocionDetalleCreateDtoSetToPromocionDetalleSet( source.getDetalles() );
            if ( set != null ) {
                entity.getDetalles().clear();
                entity.getDetalles().addAll( set );
            }
            else {
                entity.setDetalles( null );
            }
        }
        else {
            Set<PromocionDetalle> set = promocionDetalleCreateDtoSetToPromocionDetalleSet( source.getDetalles() );
            if ( set != null ) {
                entity.setDetalles( set );
            }
        }

        return entity;
    }

    @Override
    public List<PromocionDto> toDTOsList(List<Promocion> source) {
        if ( source == null ) {
            return null;
        }

        List<PromocionDto> list = new ArrayList<PromocionDto>( source.size() );
        for ( Promocion promocion : source ) {
            list.add( toDTO( promocion ) );
        }

        return list;
    }

    @Override
    public Promocion toEntityCreate(PromocionCreateDto source) {
        if ( source == null ) {
            return null;
        }

        Promocion.PromocionBuilder<?, ?> promocion = Promocion.builder();

        promocion.sucursales( longSetToSucursalSet( source.getIdSucursales() ) );
        promocion.id( source.getId() );
        promocion.eliminado( source.isEliminado() );
        promocion.denominacion( source.getDenominacion() );
        promocion.fechaDesde( source.getFechaDesde() );
        promocion.fechaHasta( source.getFechaHasta() );
        promocion.horaDesde( source.getHoraDesde() );
        promocion.horaHasta( source.getHoraHasta() );
        promocion.descripcionDescuento( source.getDescripcionDescuento() );
        promocion.precioPromocional( source.getPrecioPromocional() );
        promocion.tipoPromocion( source.getTipoPromocion() );
        promocion.detalles( promocionDetalleCreateDtoSetToPromocionDetalleSet( source.getDetalles() ) );

        return promocion.build();
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

    protected Set<PromocionDetalleDto> promocionDetalleSetToPromocionDetalleDtoSet(Set<PromocionDetalle> set) {
        if ( set == null ) {
            return null;
        }

        Set<PromocionDetalleDto> set1 = new LinkedHashSet<PromocionDetalleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PromocionDetalle promocionDetalle : set ) {
            set1.add( promocionDetalleMapper.toDTO( promocionDetalle ) );
        }

        return set1;
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

    protected Set<PromocionDetalle> promocionDetalleDtoSetToPromocionDetalleSet(Set<PromocionDetalleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<PromocionDetalle> set1 = new LinkedHashSet<PromocionDetalle>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PromocionDetalleDto promocionDetalleDto : set ) {
            set1.add( promocionDetalleMapper.toEntity( promocionDetalleDto ) );
        }

        return set1;
    }

    protected Set<PromocionDetalle> promocionDetalleCreateDtoSetToPromocionDetalleSet(Set<PromocionDetalleCreateDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<PromocionDetalle> set1 = new LinkedHashSet<PromocionDetalle>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PromocionDetalleCreateDto promocionDetalleCreateDto : set ) {
            set1.add( promocionDetalleMapper.toEntityCreate( promocionDetalleCreateDto ) );
        }

        return set1;
    }

    protected Set<Sucursal> longSetToSucursalSet(Set<Long> set) {
        if ( set == null ) {
            return null;
        }

        Set<Sucursal> set1 = new LinkedHashSet<Sucursal>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Long long1 : set ) {
            set1.add( sucursalService.getById( long1 ) );
        }

        return set1;
    }
}

