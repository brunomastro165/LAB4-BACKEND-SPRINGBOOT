package com.example.buensaborback.business.mapper.MappersImpl;

import com.example.buensaborback.business.mapper.ArticuloInsumoMapper;
import com.example.buensaborback.business.mapper.ArticuloManufacturadoMapper;
import com.example.buensaborback.business.mapper.PedidoMapper;
import com.example.buensaborback.business.services.ClienteService;
import com.example.buensaborback.business.services.SucursalService;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.Cliente.ClienteDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoCreateDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioCreateDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioDto;
import com.example.buensaborback.domain.dto.Empleado.EmpleadoDto;
import com.example.buensaborback.domain.dto.Empresa.EmpresaDto;
import com.example.buensaborback.domain.dto.Factura.FacturaCreateDto;
import com.example.buensaborback.domain.dto.Factura.FacturaDto;
import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteDto;
import com.example.buensaborback.domain.dto.ImagenEmpresa.ImagenEmpresaDto;
import com.example.buensaborback.domain.dto.ImagenSucursal.ImagenSucursalDto;
import com.example.buensaborback.domain.dto.Localidad.LocalidadDto;
import com.example.buensaborback.domain.dto.Pais.PaisDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoCreateDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoShortDto;
import com.example.buensaborback.domain.dto.Provincia.ProvinciaDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalDto;
import com.example.buensaborback.domain.dto.UsuarioCliente.UsuarioClienteDto;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Component
public class PedidoMapperImpl implements PedidoMapper {
    @Autowired
    ArticuloRepository articuloRepository;
    @Autowired
    ArticuloInsumoMapper articuloInsumoMapper;
    @Autowired
    ArticuloManufacturadoMapper articuloManufacturadoMapper;
    @Autowired
    private SucursalService sucursalService;
    @Autowired
    private ClienteService clienteService;

    @Override
    public PedidoDto toDTO(Pedido source) {
        if ( source == null ) {
            return null;
        }

        PedidoDto pedidoDto = new PedidoDto();

        pedidoDto.setId( source.getId() );
        if ( source.isEliminado() != null ) {
            pedidoDto.setEliminado( source.isEliminado() );
        }
        pedidoDto.setDetallesPedido( detallePedidoSetToDetallePedidoDtoSet( source.getDetallesPedido() ) );
        pedidoDto.setHoraEstimadaFinalizacion( source.getHoraEstimadaFinalizacion() );
        pedidoDto.setTotal( source.getTotal() );
        pedidoDto.setTotalCosto( source.getTotalCosto() );
        pedidoDto.setEstado( source.getEstado() );
        pedidoDto.setTipoEnvio( source.getTipoEnvio() );
        pedidoDto.setFormaPago( source.getFormaPago() );
        pedidoDto.setFechaPedido( source.getFechaPedido() );
        pedidoDto.setDomicilio( domicilioToDomicilioCreateDto( source.getDomicilio() ) );
        pedidoDto.setCliente( clienteToClienteDto( source.getCliente() ) );
        pedidoDto.setSucursal( sucursalToSucursalDto( source.getSucursal() ) );
        pedidoDto.setEmpleado( empleadoToEmpleadoDto( source.getEmpleado() ) );
        pedidoDto.setFactura( facturaToFacturaDto( source.getFactura() ) );

        return pedidoDto;
    }

    @Override
    public Pedido toEntity(PedidoDto source) {
        if ( source == null ) {
            return null;
        }

        Pedido.PedidoBuilder<?, ?> pedido = Pedido.builder();

        pedido.id( source.getId() );
        pedido.eliminado( source.isEliminado() );
        pedido.horaEstimadaFinalizacion( source.getHoraEstimadaFinalizacion() );
        pedido.total( source.getTotal() );
        pedido.totalCosto( source.getTotalCosto() );
        pedido.estado( source.getEstado() );
        pedido.tipoEnvio( source.getTipoEnvio() );
        pedido.formaPago( source.getFormaPago() );
        pedido.fechaPedido( source.getFechaPedido() );
        pedido.domicilio( domicilioCreateDtoToDomicilio( source.getDomicilio() ) );
        pedido.sucursal( sucursalDtoToSucursal( source.getSucursal() ) );
        pedido.factura( facturaDtoToFactura( source.getFactura() ) );
        pedido.cliente( clienteDtoToCliente( source.getCliente() ) );
        pedido.detallesPedido( detallePedidoDtoSetToDetallePedidoSet( source.getDetallesPedido() ) );
        pedido.empleado( empleadoDtoToEmpleado( source.getEmpleado() ) );

        return pedido.build();
    }

    @Override
    public Pedido toUpdate(Pedido entity, PedidoCreateDto source) {
        if ( source == null ) {
            return entity;
        }

        entity.setId( source.getId() );
        entity.setEliminado( source.isEliminado() );
        entity.setHoraEstimadaFinalizacion( source.getHoraEstimadaFinalizacion() );
        entity.setTotal( source.getTotal() );
        entity.setTotalCosto( source.getTotalCosto() );
        entity.setEstado( source.getEstado() );
        entity.setTipoEnvio( source.getTipoEnvio() );
        entity.setFormaPago( source.getFormaPago() );
        entity.setFechaPedido( source.getFechaPedido() );
        if ( source.getDomicilio() != null ) {
            if ( entity.getDomicilio() == null ) {
                entity.setDomicilio( Domicilio.builder().build() );
            }
            domicilioCreateDtoToDomicilio1( source.getDomicilio(), entity.getDomicilio() );
        }
        else {
            entity.setDomicilio( null );
        }
        if ( source.getFactura() != null ) {
            if ( entity.getFactura() == null ) {
                entity.setFactura( Factura.builder().build() );
            }
            facturaCreateDtoToFactura( source.getFactura(), entity.getFactura() );
        }
        else {
            entity.setFactura( null );
        }
        if ( entity.getDetallesPedido() != null ) {
            Set<DetallePedido> set = detallePedidoCreateDtoListToDetallePedidoSet( source.getDetallesPedido() );
            if ( set != null ) {
                entity.getDetallesPedido().clear();
                entity.getDetallesPedido().addAll( set );
            }
            else {
                entity.setDetallesPedido( null );
            }
        }
        else {
            Set<DetallePedido> set = detallePedidoCreateDtoListToDetallePedidoSet( source.getDetallesPedido() );
            if ( set != null ) {
                entity.setDetallesPedido( set );
            }
        }
        if ( source.getEmpleado() != null ) {
            if ( entity.getEmpleado() == null ) {
                entity.setEmpleado( Empleado.builder().build() );
            }
            empleadoDtoToEmpleado1( source.getEmpleado(), entity.getEmpleado() );
        }
        else {
            entity.setEmpleado( null );
        }

        return entity;
    }

    @Override
    public List<PedidoDto> toDTOsList(List<Pedido> source) {
        if ( source == null ) {
            return null;
        }

        List<PedidoDto> list = new ArrayList<PedidoDto>( source.size() );
        for ( Pedido pedido : source ) {
            list.add( toDTO( pedido ) );
        }

        return list;
    }

    @Override
    public Pedido toEntityCreate(PedidoCreateDto source) {
        if ( source == null ) {
            return null;
        }

        Pedido.PedidoBuilder<?, ?> pedido = Pedido.builder();

        pedido.sucursal( sucursalService.getById( source.getIdSucursal() ) );
        pedido.cliente( clienteService.getById( source.getIdCliente() ) );
        pedido.id( source.getId() );
        pedido.eliminado( source.isEliminado() );
        pedido.horaEstimadaFinalizacion( source.getHoraEstimadaFinalizacion() );
        pedido.total( source.getTotal() );
        pedido.totalCosto( source.getTotalCosto() );
        pedido.estado( source.getEstado() );
        pedido.tipoEnvio( source.getTipoEnvio() );
        pedido.formaPago( source.getFormaPago() );
        pedido.fechaPedido( source.getFechaPedido() );
        pedido.domicilio( domicilioCreateDtoToDomicilio( source.getDomicilio() ) );
        pedido.factura( facturaCreateDtoToFactura1( source.getFactura() ) );
        pedido.detallesPedido( detallePedidoCreateDtoListToDetallePedidoSet( source.getDetallesPedido() ) );
        pedido.empleado( empleadoDtoToEmpleado( source.getEmpleado() ) );

        return pedido.build();
    }





    protected DomicilioCreateDto domicilioToDomicilioCreateDto(Domicilio domicilio) {
        if ( domicilio == null ) {
            return null;
        }

        DomicilioCreateDto domicilioCreateDto = new DomicilioCreateDto();

        domicilioCreateDto.setCalle( domicilio.getCalle() );
        domicilioCreateDto.setNumero( domicilio.getNumero() );
        domicilioCreateDto.setCp( domicilio.getCp() );
        domicilioCreateDto.setPiso( domicilio.getPiso() );
        domicilioCreateDto.setNroDpto( domicilio.getNroDpto() );

        return domicilioCreateDto;
    }

    protected UsuarioClienteDto usuarioClienteToUsuarioClienteDto(UsuarioCliente usuarioCliente) {
        if ( usuarioCliente == null ) {
            return null;
        }

        Long id = null;
        String userName = null;
        String auth0Id = null;

        id = usuarioCliente.getId();
        userName = usuarioCliente.getUserName();
        auth0Id = usuarioCliente.getAuth0Id();

        UsuarioClienteDto usuarioClienteDto = new UsuarioClienteDto( id, userName, auth0Id );

        if ( usuarioCliente.isEliminado() != null ) {
            usuarioClienteDto.setEliminado( usuarioCliente.isEliminado() );
        }

        return usuarioClienteDto;
    }

    protected ImagenClienteDto imagenClienteToImagenClienteDto(ImagenCliente imagenCliente) {
        if ( imagenCliente == null ) {
            return null;
        }

        ImagenClienteDto imagenClienteDto = new ImagenClienteDto();

        imagenClienteDto.setName( imagenCliente.getName() );
        imagenClienteDto.setUrl( imagenCliente.getUrl() );

        return imagenClienteDto;
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

    protected Set<DomicilioDto> domicilioSetToDomicilioDtoSet(Set<Domicilio> set) {
        if ( set == null ) {
            return null;
        }

        Set<DomicilioDto> set1 = new LinkedHashSet<DomicilioDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Domicilio domicilio : set ) {
            set1.add( domicilioToDomicilioDto( domicilio ) );
        }

        return set1;
    }

    protected PedidoShortDto pedidoToPedidoShortDto(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }

        PedidoShortDto pedidoShortDto = new PedidoShortDto();

        pedidoShortDto.setId( pedido.getId() );
        if ( pedido.isEliminado() != null ) {
            pedidoShortDto.setEliminado( pedido.isEliminado() );
        }
        pedidoShortDto.setDetallesPedido( detallePedidoSetToDetallePedidoDtoSet( pedido.getDetallesPedido() ) );
        pedidoShortDto.setHoraEstimadaFinalizacion( pedido.getHoraEstimadaFinalizacion() );
        pedidoShortDto.setTotal( pedido.getTotal() );
        pedidoShortDto.setTotalCosto( pedido.getTotalCosto() );
        pedidoShortDto.setEstado( pedido.getEstado() );
        pedidoShortDto.setTipoEnvio( pedido.getTipoEnvio() );
        pedidoShortDto.setFormaPago( pedido.getFormaPago() );
        pedidoShortDto.setFechaPedido( pedido.getFechaPedido() );

        return pedidoShortDto;
    }

    protected Set<PedidoShortDto> pedidoSetToPedidoShortDtoSet(Set<Pedido> set) {
        if ( set == null ) {
            return null;
        }

        Set<PedidoShortDto> set1 = new LinkedHashSet<PedidoShortDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Pedido pedido : set ) {
            set1.add( pedidoToPedidoShortDto( pedido ) );
        }

        return set1;
    }

    protected ClienteDto clienteToClienteDto(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDto clienteDto = new ClienteDto();

        clienteDto.setId( cliente.getId() );
        if ( cliente.isEliminado() != null ) {
            clienteDto.setEliminado( cliente.isEliminado() );
        }
        clienteDto.setNombre( cliente.getNombre() );
        clienteDto.setApellido( cliente.getApellido() );
        clienteDto.setTelefono( cliente.getTelefono() );
        clienteDto.setEmail( cliente.getEmail() );
        clienteDto.setUsuario( usuarioClienteToUsuarioClienteDto( cliente.getUsuario() ) );
        clienteDto.setImagenCliente( imagenClienteToImagenClienteDto( cliente.getImagenCliente() ) );
        clienteDto.setDomicilios( domicilioSetToDomicilioDtoSet( cliente.getDomicilios() ) );
        clienteDto.setPedidos( pedidoSetToPedidoShortDtoSet( cliente.getPedidos() ) );

        return clienteDto;
    }

    protected ImagenEmpresaDto imagenEmpresaToImagenEmpresaDto(ImagenEmpresa imagenEmpresa) {
        if ( imagenEmpresa == null ) {
            return null;
        }

        ImagenEmpresaDto imagenEmpresaDto = new ImagenEmpresaDto();

        imagenEmpresaDto.setName( imagenEmpresa.getName() );
        imagenEmpresaDto.setUrl( imagenEmpresa.getUrl() );

        return imagenEmpresaDto;
    }

    protected List<ImagenEmpresaDto> imagenEmpresaSetToImagenEmpresaDtoList(Set<ImagenEmpresa> set) {
        if ( set == null ) {
            return null;
        }

        List<ImagenEmpresaDto> list = new ArrayList<ImagenEmpresaDto>( set.size() );
        for ( ImagenEmpresa imagenEmpresa : set ) {
            list.add( imagenEmpresaToImagenEmpresaDto( imagenEmpresa ) );
        }

        return list;
    }

    protected EmpresaDto empresaToEmpresaDto(Empresa empresa) {
        if ( empresa == null ) {
            return null;
        }

        EmpresaDto empresaDto = new EmpresaDto();

        empresaDto.setId( empresa.getId() );
        if ( empresa.isEliminado() != null ) {
            empresaDto.setEliminado( empresa.isEliminado() );
        }
        empresaDto.setNombre( empresa.getNombre() );
        empresaDto.setRazonSocial( empresa.getRazonSocial() );
        empresaDto.setCuil( empresa.getCuil() );
        empresaDto.setImagenes( imagenEmpresaSetToImagenEmpresaDtoList( empresa.getImagenes() ) );

        return empresaDto;
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

    protected SucursalDto sucursalToSucursalDto(Sucursal sucursal) {
        if ( sucursal == null ) {
            return null;
        }

        SucursalDto sucursalDto = new SucursalDto();

        sucursalDto.setId( sucursal.getId() );
        if ( sucursal.isEliminado() != null ) {
            sucursalDto.setEliminado( sucursal.isEliminado() );
        }
        sucursalDto.setNombre( sucursal.getNombre() );
        sucursalDto.setHorarioApertura( sucursal.getHorarioApertura() );
        sucursalDto.setHorarioCierre( sucursal.getHorarioCierre() );
        sucursalDto.setEsCasaMatriz( sucursal.getEsCasaMatriz() );
        sucursalDto.setDomicilio( domicilioToDomicilioDto( sucursal.getDomicilio() ) );
        sucursalDto.setEmpresa( empresaToEmpresaDto( sucursal.getEmpresa() ) );
        sucursalDto.setImagenes( imagenSucursalSetToImagenSucursalDtoList( sucursal.getImagenes() ) );

        return sucursalDto;
    }

    protected Set<PedidoDto> pedidoSetToPedidoDtoSet(Set<Pedido> set) {
        if ( set == null ) {
            return null;
        }

        Set<PedidoDto> set1 = new LinkedHashSet<PedidoDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Pedido pedido : set ) {
            set1.add( toDTO( pedido ) );
        }

        return set1;
    }

    protected EmpleadoDto empleadoToEmpleadoDto(Empleado empleado) {
        if ( empleado == null ) {
            return null;
        }

        EmpleadoDto empleadoDto = new EmpleadoDto();

        empleadoDto.setId( empleado.getId() );
        if ( empleado.isEliminado() != null ) {
            empleadoDto.setEliminado( empleado.isEliminado() );
        }
        empleadoDto.setNombre( empleado.getNombre() );
        empleadoDto.setApellido( empleado.getApellido() );
        empleadoDto.setTelefono( empleado.getTelefono() );
        empleadoDto.setEmail( empleado.getEmail() );
        empleadoDto.setDomicilios( domicilioSetToDomicilioDtoSet( empleado.getDomicilios() ) );
        empleadoDto.setTipoEmpleado( empleado.getTipoEmpleado() );
        empleadoDto.setPedidos( pedidoSetToPedidoDtoSet( empleado.getPedidos() ) );
        empleadoDto.setSucursal( sucursalToSucursalDto( empleado.getSucursal() ) );

        return empleadoDto;
    }

    protected FacturaDto facturaToFacturaDto(Factura factura) {
        if ( factura == null ) {
            return null;
        }

        FacturaDto facturaDto = new FacturaDto();

        facturaDto.setId( factura.getId() );
        if ( factura.isEliminado() != null ) {
            facturaDto.setEliminado( factura.isEliminado() );
        }
        facturaDto.setFechaFcturacion( factura.getFechaFcturacion() );
        facturaDto.setMpPaymentId( factura.getMpPaymentId() );
        facturaDto.setMpMerchantOrderId( factura.getMpMerchantOrderId() );
        facturaDto.setMpPreferenceId( factura.getMpPreferenceId() );
        facturaDto.setMpPaymentType( factura.getMpPaymentType() );
        facturaDto.setFormaPago( factura.getFormaPago() );
        facturaDto.setTotalVenta( factura.getTotalVenta() );

        return facturaDto;
    }

    protected Domicilio domicilioCreateDtoToDomicilio(DomicilioCreateDto domicilioCreateDto) {
        if ( domicilioCreateDto == null ) {
            return null;
        }

        Domicilio.DomicilioBuilder<?, ?> domicilio = Domicilio.builder();

        domicilio.calle( domicilioCreateDto.getCalle() );
        domicilio.numero( domicilioCreateDto.getNumero() );
        domicilio.cp( domicilioCreateDto.getCp() );
        domicilio.piso( domicilioCreateDto.getPiso() );
        domicilio.nroDpto( domicilioCreateDto.getNroDpto() );

        return domicilio.build();
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

    protected ImagenEmpresa imagenEmpresaDtoToImagenEmpresa(ImagenEmpresaDto imagenEmpresaDto) {
        if ( imagenEmpresaDto == null ) {
            return null;
        }

        ImagenEmpresa.ImagenEmpresaBuilder<?, ?> imagenEmpresa = ImagenEmpresa.builder();

        imagenEmpresa.name( imagenEmpresaDto.getName() );
        imagenEmpresa.url( imagenEmpresaDto.getUrl() );

        return imagenEmpresa.build();
    }

    protected Set<ImagenEmpresa> imagenEmpresaDtoListToImagenEmpresaSet(List<ImagenEmpresaDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<ImagenEmpresa> set = new LinkedHashSet<ImagenEmpresa>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( ImagenEmpresaDto imagenEmpresaDto : list ) {
            set.add( imagenEmpresaDtoToImagenEmpresa( imagenEmpresaDto ) );
        }

        return set;
    }

    protected Empresa empresaDtoToEmpresa(EmpresaDto empresaDto) {
        if ( empresaDto == null ) {
            return null;
        }

        Empresa.EmpresaBuilder<?, ?> empresa = Empresa.builder();

        empresa.id( empresaDto.getId() );
        empresa.eliminado( empresaDto.isEliminado() );
        empresa.nombre( empresaDto.getNombre() );
        empresa.razonSocial( empresaDto.getRazonSocial() );
        empresa.cuil( empresaDto.getCuil() );
        empresa.imagenes( imagenEmpresaDtoListToImagenEmpresaSet( empresaDto.getImagenes() ) );

        return empresa.build();
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

    protected Sucursal sucursalDtoToSucursal(SucursalDto sucursalDto) {
        if ( sucursalDto == null ) {
            return null;
        }

        Sucursal.SucursalBuilder<?, ?> sucursal = Sucursal.builder();

        sucursal.id( sucursalDto.getId() );
        sucursal.eliminado( sucursalDto.isEliminado() );
        sucursal.nombre( sucursalDto.getNombre() );
        sucursal.horarioApertura( sucursalDto.getHorarioApertura() );
        sucursal.horarioCierre( sucursalDto.getHorarioCierre() );
        sucursal.esCasaMatriz( sucursalDto.getEsCasaMatriz() );
        sucursal.domicilio( domicilioDtoToDomicilio( sucursalDto.getDomicilio() ) );
        sucursal.empresa( empresaDtoToEmpresa( sucursalDto.getEmpresa() ) );
        sucursal.imagenes( imagenSucursalDtoListToImagenSucursalSet( sucursalDto.getImagenes() ) );

        return sucursal.build();
    }

    protected Factura facturaDtoToFactura(FacturaDto facturaDto) {
        if ( facturaDto == null ) {
            return null;
        }

        Factura.FacturaBuilder<?, ?> factura = Factura.builder();

        factura.id( facturaDto.getId() );
        factura.eliminado( facturaDto.isEliminado() );
        factura.fechaFcturacion( facturaDto.getFechaFcturacion() );
        factura.mpPaymentId( facturaDto.getMpPaymentId() );
        factura.mpMerchantOrderId( facturaDto.getMpMerchantOrderId() );
        factura.mpPreferenceId( facturaDto.getMpPreferenceId() );
        factura.mpPaymentType( facturaDto.getMpPaymentType() );
        factura.formaPago( facturaDto.getFormaPago() );
        factura.totalVenta( facturaDto.getTotalVenta() );

        return factura.build();
    }

    protected UsuarioCliente usuarioClienteDtoToUsuarioCliente(UsuarioClienteDto usuarioClienteDto) {
        if ( usuarioClienteDto == null ) {
            return null;
        }

        UsuarioCliente.UsuarioClienteBuilder usuarioCliente = UsuarioCliente.builder();

        usuarioCliente.auth0Id( usuarioClienteDto.getAuth0Id() );
        usuarioCliente.userName( usuarioClienteDto.getUserName() );

        return usuarioCliente.build();
    }

    protected ImagenCliente imagenClienteDtoToImagenCliente(ImagenClienteDto imagenClienteDto) {
        if ( imagenClienteDto == null ) {
            return null;
        }

        ImagenCliente.ImagenClienteBuilder<?, ?> imagenCliente = ImagenCliente.builder();

        imagenCliente.name( imagenClienteDto.getName() );
        imagenCliente.url( imagenClienteDto.getUrl() );

        return imagenCliente.build();
    }

    protected Set<Domicilio> domicilioDtoSetToDomicilioSet(Set<DomicilioDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Domicilio> set1 = new LinkedHashSet<Domicilio>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( DomicilioDto domicilioDto : set ) {
            set1.add( domicilioDtoToDomicilio( domicilioDto ) );
        }

        return set1;
    }

    protected DetallePedido detallePedidoDtoToDetallePedido(DetallePedidoDto detallePedidoDto) {
        if ( detallePedidoDto == null ) {
            return null;
        }

        DetallePedido.DetallePedidoBuilder<?, ?> detallePedido = DetallePedido.builder();

        detallePedido.id( detallePedidoDto.getId() );
        detallePedido.eliminado( detallePedidoDto.isEliminado() );
        detallePedido.cantidad( detallePedidoDto.getCantidad() );
        detallePedido.subTotal( detallePedidoDto.getSubTotal() );

        return detallePedido.build();
    }

    protected Set<DetallePedido> detallePedidoDtoSetToDetallePedidoSet(Set<DetallePedidoDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<DetallePedido> set1 = new LinkedHashSet<DetallePedido>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( DetallePedidoDto detallePedidoDto : set ) {
            set1.add( detallePedidoDtoToDetallePedido( detallePedidoDto ) );
        }

        return set1;
    }

    protected Pedido pedidoShortDtoToPedido(PedidoShortDto pedidoShortDto) {
        if ( pedidoShortDto == null ) {
            return null;
        }

        Pedido.PedidoBuilder<?, ?> pedido = Pedido.builder();

        pedido.id( pedidoShortDto.getId() );
        pedido.eliminado( pedidoShortDto.isEliminado() );
        pedido.horaEstimadaFinalizacion( pedidoShortDto.getHoraEstimadaFinalizacion() );
        pedido.total( pedidoShortDto.getTotal() );
        pedido.totalCosto( pedidoShortDto.getTotalCosto() );
        pedido.estado( pedidoShortDto.getEstado() );
        pedido.tipoEnvio( pedidoShortDto.getTipoEnvio() );
        pedido.formaPago( pedidoShortDto.getFormaPago() );
        pedido.fechaPedido( pedidoShortDto.getFechaPedido() );
        pedido.detallesPedido( detallePedidoDtoSetToDetallePedidoSet( pedidoShortDto.getDetallesPedido() ) );

        return pedido.build();
    }

    protected Set<Pedido> pedidoShortDtoSetToPedidoSet(Set<PedidoShortDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Pedido> set1 = new LinkedHashSet<Pedido>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PedidoShortDto pedidoShortDto : set ) {
            set1.add( pedidoShortDtoToPedido( pedidoShortDto ) );
        }

        return set1;
    }

    protected Cliente clienteDtoToCliente(ClienteDto clienteDto) {
        if ( clienteDto == null ) {
            return null;
        }

        Cliente.ClienteBuilder<?, ?> cliente = Cliente.builder();

        cliente.id( clienteDto.getId() );
        cliente.eliminado( clienteDto.isEliminado() );
        cliente.nombre( clienteDto.getNombre() );
        cliente.apellido( clienteDto.getApellido() );
        cliente.telefono( clienteDto.getTelefono() );
        cliente.email( clienteDto.getEmail() );
        cliente.usuario( usuarioClienteDtoToUsuarioCliente( clienteDto.getUsuario() ) );
        cliente.imagenCliente( imagenClienteDtoToImagenCliente( clienteDto.getImagenCliente() ) );
        cliente.domicilios( domicilioDtoSetToDomicilioSet( clienteDto.getDomicilios() ) );
        cliente.pedidos( pedidoShortDtoSetToPedidoSet( clienteDto.getPedidos() ) );

        return cliente.build();
    }

    protected Set<Pedido> pedidoDtoSetToPedidoSet(Set<PedidoDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Pedido> set1 = new LinkedHashSet<Pedido>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PedidoDto pedidoDto : set ) {
            set1.add( toEntity( pedidoDto ) );
        }

        return set1;
    }

    protected Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto) {
        if ( empleadoDto == null ) {
            return null;
        }

        Empleado.EmpleadoBuilder<?, ?> empleado = Empleado.builder();

        empleado.id( empleadoDto.getId() );
        empleado.eliminado( empleadoDto.isEliminado() );
        empleado.nombre( empleadoDto.getNombre() );
        empleado.apellido( empleadoDto.getApellido() );
        empleado.telefono( empleadoDto.getTelefono() );
        empleado.email( empleadoDto.getEmail() );
        empleado.domicilios( domicilioDtoSetToDomicilioSet( empleadoDto.getDomicilios() ) );
        empleado.tipoEmpleado( empleadoDto.getTipoEmpleado() );
        empleado.pedidos( pedidoDtoSetToPedidoSet( empleadoDto.getPedidos() ) );
        empleado.sucursal( sucursalDtoToSucursal( empleadoDto.getSucursal() ) );

        return empleado.build();
    }

    protected void domicilioCreateDtoToDomicilio1(DomicilioCreateDto domicilioCreateDto, Domicilio mappingTarget) {
        if ( domicilioCreateDto == null ) {
            return;
        }

        mappingTarget.setCalle( domicilioCreateDto.getCalle() );
        mappingTarget.setNumero( domicilioCreateDto.getNumero() );
        mappingTarget.setCp( domicilioCreateDto.getCp() );
        mappingTarget.setPiso( domicilioCreateDto.getPiso() );
        mappingTarget.setNroDpto( domicilioCreateDto.getNroDpto() );
    }

    protected void facturaCreateDtoToFactura(FacturaCreateDto facturaCreateDto, Factura mappingTarget) {
        if ( facturaCreateDto == null ) {
            return;
        }

        mappingTarget.setId( facturaCreateDto.getId() );
        mappingTarget.setEliminado( facturaCreateDto.isEliminado() );
        mappingTarget.setFechaFcturacion( facturaCreateDto.getFechaFcturacion() );
        mappingTarget.setMpPaymentId( facturaCreateDto.getMpPaymentId() );
        mappingTarget.setMpMerchantOrderId( facturaCreateDto.getMpMerchantOrderId() );
        mappingTarget.setMpPreferenceId( facturaCreateDto.getMpPreferenceId() );
        mappingTarget.setMpPaymentType( facturaCreateDto.getMpPaymentType() );
        mappingTarget.setFormaPago( facturaCreateDto.getFormaPago() );
        mappingTarget.setTotalVenta( facturaCreateDto.getTotalVenta() );
    }

    protected DetallePedido detallePedidoCreateDtoToDetallePedido(DetallePedidoCreateDto detallePedidoCreateDto) {
        if ( detallePedidoCreateDto == null ) {
            return null;
        }

        DetallePedido.DetallePedidoBuilder<?, ?> detallePedido = DetallePedido.builder();

        detallePedido.id( detallePedidoCreateDto.getId() );
        detallePedido.eliminado( detallePedidoCreateDto.isEliminado() );
        detallePedido.cantidad( detallePedidoCreateDto.getCantidad() );
        detallePedido.subTotal( detallePedidoCreateDto.getSubTotal() );

        return detallePedido.build();
    }

    protected Set<DetallePedido> detallePedidoCreateDtoListToDetallePedidoSet(List<DetallePedidoCreateDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<DetallePedido> set = new LinkedHashSet<DetallePedido>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( DetallePedidoCreateDto detallePedidoCreateDto : list ) {
            set.add( detallePedidoCreateDtoToDetallePedido( detallePedidoCreateDto ) );
        }

        return set;
    }

    protected void paisDtoToPais1(PaisDto paisDto, Pais mappingTarget) {
        if ( paisDto == null ) {
            return;
        }

        mappingTarget.setId( paisDto.getId() );
        mappingTarget.setEliminado( paisDto.isEliminado() );
        mappingTarget.setNombre( paisDto.getNombre() );
    }

    protected void provinciaDtoToProvincia1(ProvinciaDto provinciaDto, Provincia mappingTarget) {
        if ( provinciaDto == null ) {
            return;
        }

        mappingTarget.setId( provinciaDto.getId() );
        mappingTarget.setEliminado( provinciaDto.isEliminado() );
        mappingTarget.setNombre( provinciaDto.getNombre() );
        if ( provinciaDto.getPais() != null ) {
            if ( mappingTarget.getPais() == null ) {
                mappingTarget.setPais( Pais.builder().build() );
            }
            paisDtoToPais1( provinciaDto.getPais(), mappingTarget.getPais() );
        }
        else {
            mappingTarget.setPais( null );
        }
    }

    protected void localidadDtoToLocalidad1(LocalidadDto localidadDto, Localidad mappingTarget) {
        if ( localidadDto == null ) {
            return;
        }

        mappingTarget.setId( localidadDto.getId() );
        mappingTarget.setEliminado( localidadDto.isEliminado() );
        mappingTarget.setNombre( localidadDto.getNombre() );
        if ( localidadDto.getProvincia() != null ) {
            if ( mappingTarget.getProvincia() == null ) {
                mappingTarget.setProvincia( Provincia.builder().build() );
            }
            provinciaDtoToProvincia1( localidadDto.getProvincia(), mappingTarget.getProvincia() );
        }
        else {
            mappingTarget.setProvincia( null );
        }
    }

    protected void domicilioDtoToDomicilio1(DomicilioDto domicilioDto, Domicilio mappingTarget) {
        if ( domicilioDto == null ) {
            return;
        }

        mappingTarget.setId( domicilioDto.getId() );
        mappingTarget.setEliminado( domicilioDto.isEliminado() );
        mappingTarget.setCalle( domicilioDto.getCalle() );
        mappingTarget.setNumero( domicilioDto.getNumero() );
        mappingTarget.setCp( domicilioDto.getCp() );
        mappingTarget.setPiso( domicilioDto.getPiso() );
        mappingTarget.setNroDpto( domicilioDto.getNroDpto() );
        if ( domicilioDto.getLocalidad() != null ) {
            if ( mappingTarget.getLocalidad() == null ) {
                mappingTarget.setLocalidad( Localidad.builder().build() );
            }
            localidadDtoToLocalidad1( domicilioDto.getLocalidad(), mappingTarget.getLocalidad() );
        }
        else {
            mappingTarget.setLocalidad( null );
        }
    }

    protected void empresaDtoToEmpresa1(EmpresaDto empresaDto, Empresa mappingTarget) {
        if ( empresaDto == null ) {
            return;
        }

        mappingTarget.setId( empresaDto.getId() );
        mappingTarget.setEliminado( empresaDto.isEliminado() );
        mappingTarget.setNombre( empresaDto.getNombre() );
        mappingTarget.setRazonSocial( empresaDto.getRazonSocial() );
        mappingTarget.setCuil( empresaDto.getCuil() );
        if ( mappingTarget.getImagenes() != null ) {
            Set<ImagenEmpresa> set = imagenEmpresaDtoListToImagenEmpresaSet( empresaDto.getImagenes() );
            if ( set != null ) {
                mappingTarget.getImagenes().clear();
                mappingTarget.getImagenes().addAll( set );
            }
            else {
                mappingTarget.setImagenes( null );
            }
        }
        else {
            Set<ImagenEmpresa> set = imagenEmpresaDtoListToImagenEmpresaSet( empresaDto.getImagenes() );
            if ( set != null ) {
                mappingTarget.setImagenes( set );
            }
        }
    }

    protected void sucursalDtoToSucursal1(SucursalDto sucursalDto, Sucursal mappingTarget) {
        if ( sucursalDto == null ) {
            return;
        }

        mappingTarget.setId( sucursalDto.getId() );
        mappingTarget.setEliminado( sucursalDto.isEliminado() );
        mappingTarget.setNombre( sucursalDto.getNombre() );
        mappingTarget.setHorarioApertura( sucursalDto.getHorarioApertura() );
        mappingTarget.setHorarioCierre( sucursalDto.getHorarioCierre() );
        mappingTarget.setEsCasaMatriz( sucursalDto.getEsCasaMatriz() );
        if ( sucursalDto.getDomicilio() != null ) {
            if ( mappingTarget.getDomicilio() == null ) {
                mappingTarget.setDomicilio( Domicilio.builder().build() );
            }
            domicilioDtoToDomicilio1( sucursalDto.getDomicilio(), mappingTarget.getDomicilio() );
        }
        else {
            mappingTarget.setDomicilio( null );
        }
        if ( sucursalDto.getEmpresa() != null ) {
            if ( mappingTarget.getEmpresa() == null ) {
                mappingTarget.setEmpresa( Empresa.builder().build() );
            }
            empresaDtoToEmpresa1( sucursalDto.getEmpresa(), mappingTarget.getEmpresa() );
        }
        else {
            mappingTarget.setEmpresa( null );
        }
        if ( mappingTarget.getImagenes() != null ) {
            Set<ImagenSucursal> set = imagenSucursalDtoListToImagenSucursalSet( sucursalDto.getImagenes() );
            if ( set != null ) {
                mappingTarget.getImagenes().clear();
                mappingTarget.getImagenes().addAll( set );
            }
            else {
                mappingTarget.setImagenes( null );
            }
        }
        else {
            Set<ImagenSucursal> set = imagenSucursalDtoListToImagenSucursalSet( sucursalDto.getImagenes() );
            if ( set != null ) {
                mappingTarget.setImagenes( set );
            }
        }
    }

    protected void empleadoDtoToEmpleado1(EmpleadoDto empleadoDto, Empleado mappingTarget) {
        if ( empleadoDto == null ) {
            return;
        }

        mappingTarget.setId( empleadoDto.getId() );
        mappingTarget.setEliminado( empleadoDto.isEliminado() );
        mappingTarget.setNombre( empleadoDto.getNombre() );
        mappingTarget.setApellido( empleadoDto.getApellido() );
        mappingTarget.setTelefono( empleadoDto.getTelefono() );
        mappingTarget.setEmail( empleadoDto.getEmail() );
        if ( mappingTarget.getDomicilios() != null ) {
            Set<Domicilio> set = domicilioDtoSetToDomicilioSet( empleadoDto.getDomicilios() );
            if ( set != null ) {
                mappingTarget.getDomicilios().clear();
                mappingTarget.getDomicilios().addAll( set );
            }
            else {
                mappingTarget.setDomicilios( null );
            }
        }
        else {
            Set<Domicilio> set = domicilioDtoSetToDomicilioSet( empleadoDto.getDomicilios() );
            if ( set != null ) {
                mappingTarget.setDomicilios( set );
            }
        }
        mappingTarget.setTipoEmpleado( empleadoDto.getTipoEmpleado() );
        if ( mappingTarget.getPedidos() != null ) {
            Set<Pedido> set1 = pedidoDtoSetToPedidoSet( empleadoDto.getPedidos() );
            if ( set1 != null ) {
                mappingTarget.getPedidos().clear();
                mappingTarget.getPedidos().addAll( set1 );
            }
            else {
                mappingTarget.setPedidos( null );
            }
        }
        else {
            Set<Pedido> set1 = pedidoDtoSetToPedidoSet( empleadoDto.getPedidos() );
            if ( set1 != null ) {
                mappingTarget.setPedidos( set1 );
            }
        }
        if ( empleadoDto.getSucursal() != null ) {
            if ( mappingTarget.getSucursal() == null ) {
                mappingTarget.setSucursal( Sucursal.builder().build() );
            }
            sucursalDtoToSucursal1( empleadoDto.getSucursal(), mappingTarget.getSucursal() );
        }
        else {
            mappingTarget.setSucursal( null );
        }
    }

    protected Factura facturaCreateDtoToFactura1(FacturaCreateDto facturaCreateDto) {
        if ( facturaCreateDto == null ) {
            return null;
        }

        Factura.FacturaBuilder<?, ?> factura = Factura.builder();

        factura.id( facturaCreateDto.getId() );
        factura.eliminado( facturaCreateDto.isEliminado() );
        factura.fechaFcturacion( facturaCreateDto.getFechaFcturacion() );
        factura.mpPaymentId( facturaCreateDto.getMpPaymentId() );
        factura.mpMerchantOrderId( facturaCreateDto.getMpMerchantOrderId() );
        factura.mpPreferenceId( facturaCreateDto.getMpPreferenceId() );
        factura.mpPaymentType( facturaCreateDto.getMpPaymentType() );
        factura.formaPago( facturaCreateDto.getFormaPago() );
        factura.totalVenta( facturaCreateDto.getTotalVenta() );

        return factura.build();
    }
    protected DetallePedidoDto detallePedidoToDetallePedidoDto(DetallePedido detallePedido) {
        if (detallePedido == null) {
            return null;
        }

        DetallePedidoDto detallePedidoDto = new DetallePedidoDto();

        detallePedidoDto.setId(detallePedido.getId());
        if (detallePedido.isEliminado() != null) {
            detallePedidoDto.setEliminado(detallePedido.isEliminado());
        }
        detallePedidoDto.setCantidad(detallePedido.getCantidad());
        detallePedidoDto.setSubTotal(detallePedido.getSubTotal());

        // Asigna el Articulo al DetallePedidoDto
        Articulo articulo = articuloRepository.getById(detallePedido.getArticulo().getId());
        if (articulo instanceof ArticuloManufacturado) {
            ArticuloManufacturadoDto dto = articuloManufacturadoMapper.toDTO((ArticuloManufacturado) articulo);
            detallePedidoDto.setArticuloManufacturado(dto);
        } else if (articulo instanceof ArticuloInsumo) {
            ArticuloInsumoDto insumoDto = articuloInsumoMapper.toDTO((ArticuloInsumo) articulo);
            detallePedidoDto.setArticuloInsumo(insumoDto);
        }

        return detallePedidoDto;
    }

    protected Set<DetallePedidoDto> detallePedidoSetToDetallePedidoDtoSet(Set<DetallePedido> set) {
        if (set == null) {
            return null;
        }

        Set<DetallePedidoDto> set1 = new LinkedHashSet<DetallePedidoDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (DetallePedido detallePedido : set) {
            set1.add(detallePedidoToDetallePedidoDto(detallePedido));
        }

        return set1;
    }

}


