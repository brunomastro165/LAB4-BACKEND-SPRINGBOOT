package com.example.buensaborback.business.mapper.MappersImpl;

import com.example.buensaborback.business.mapper.DomicilioMapper;
import com.example.buensaborback.business.services.LocalidadService;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioCreateDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioDto;
import com.example.buensaborback.domain.dto.Localidad.LocalidadDto;
import com.example.buensaborback.domain.dto.Pais.PaisDto;
import com.example.buensaborback.domain.dto.Provincia.ProvinciaDto;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.domain.entities.Localidad;
import com.example.buensaborback.domain.entities.Pais;
import com.example.buensaborback.domain.entities.Provincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DomicilioMapperImpl implements DomicilioMapper {

    @Autowired
    private LocalidadService localidadService;

    @Override
    public DomicilioDto toDTO(Domicilio source) {
        if ( source == null ) {
            return null;
        }

        DomicilioDto domicilioDto = new DomicilioDto();

        domicilioDto.setId( source.getId() );
        if ( source.isEliminado() != null ) {
            domicilioDto.setEliminado( source.isEliminado() );
        }
        domicilioDto.setCalle( source.getCalle() );
        domicilioDto.setNumero( source.getNumero() );
        domicilioDto.setCp( source.getCp() );
        domicilioDto.setPiso( source.getPiso() );
        domicilioDto.setNroDpto( source.getNroDpto() );
        domicilioDto.setLocalidad( localidadToLocalidadDto( source.getLocalidad() ) );

        return domicilioDto;
    }

    @Override
    public Domicilio toEntity(DomicilioDto source) {
        if ( source == null ) {
            return null;
        }

        Domicilio.DomicilioBuilder<?, ?> domicilio = Domicilio.builder();

        domicilio.id( source.getId() );
        domicilio.eliminado( source.isEliminado() );
        domicilio.calle( source.getCalle() );
        domicilio.numero( source.getNumero() );
        domicilio.cp( source.getCp() );
        domicilio.piso( source.getPiso() );
        domicilio.nroDpto( source.getNroDpto() );
        domicilio.localidad( localidadDtoToLocalidad( source.getLocalidad() ) );

        return domicilio.build();
    }

    @Override
    public Domicilio toUpdate(Domicilio entity, DomicilioCreateDto source) {
        if ( source == null ) {
            return entity;
        }
        entity.setLocalidad( localidadService.getById( source.getIdLocalidad() ) );
        entity.setCalle( source.getCalle() );
        entity.setNumero( source.getNumero() );
        entity.setCp( source.getCp() );
        entity.setPiso( source.getPiso() );
        entity.setNroDpto( source.getNroDpto() );

        return entity;
    }

    @Override
    public List<DomicilioDto> toDTOsList(List<Domicilio> source) {
        if ( source == null ) {
            return null;
        }

        List<DomicilioDto> list = new ArrayList<DomicilioDto>( source.size() );
        for ( Domicilio domicilio : source ) {
            list.add( toDTO( domicilio ) );
        }

        return list;
    }

    @Override
    public Domicilio toEntityCreate(DomicilioCreateDto source) {
        if ( source == null ) {
            return null;
        }

        Domicilio.DomicilioBuilder<?, ?> domicilio = Domicilio.builder();

        domicilio.localidad( localidadService.getById( source.getIdLocalidad() ) );
        domicilio.calle( source.getCalle() );
        domicilio.numero( source.getNumero() );
        domicilio.cp( source.getCp() );
        domicilio.piso( source.getPiso() );
        domicilio.nroDpto( source.getNroDpto() );

        return domicilio.build();
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
}



