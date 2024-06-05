package com.example.buensaborback.business.mapper.MappersImpl;

import com.example.buensaborback.business.mapper.ArticuloInsumoMapper;
import com.example.buensaborback.business.mapper.ArticuloManufacturadoMapper;
import com.example.buensaborback.business.mapper.PedidoMapper;
import com.example.buensaborback.business.services.SucursalService;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoCreateDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioCreateDto;
import com.example.buensaborback.domain.dto.Domicilio.DomicilioDto;
import com.example.buensaborback.domain.dto.Empresa.EmpresaDto;
import com.example.buensaborback.domain.dto.Factura.FacturaCreateDto;
import com.example.buensaborback.domain.dto.Factura.FacturaDto;
import com.example.buensaborback.domain.dto.ImagenEmpresa.ImagenEmpresaDto;
import com.example.buensaborback.domain.dto.ImagenSucursal.ImagenSucursalDto;
import com.example.buensaborback.domain.dto.Localidad.LocalidadDto;
import com.example.buensaborback.domain.dto.Pais.PaisDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoCreateDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoDto;
import com.example.buensaborback.domain.dto.Provincia.ProvinciaDto;
import com.example.buensaborback.domain.dto.Sucursal.SucursalDto;
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
    SucursalService sucursalService;

    @Override
    public PedidoDto toDTO(Pedido source) {
        if (source == null) {
            return null;
        }

        PedidoDto pedidoDto = new PedidoDto();

        pedidoDto.setId(source.getId());
        if (source.isEliminado() != null) {
            pedidoDto.setEliminado(source.isEliminado());
        }
        pedidoDto.setDetallesPedido(detallePedidoSetToDetallePedidoDtoSet(source.getDetallesPedido()));
        pedidoDto.setTotal(source.getTotal());
        pedidoDto.setEstado(source.getEstado());
        pedidoDto.setTipoEnvio(source.getTipoEnvio());
        pedidoDto.setDomicilio(domicilioToDomicilioCreateDto(source.getDomicilio()));
        pedidoDto.setSucursal(sucursalToSucursalDto(source.getSucursal()));
        pedidoDto.setFactura(facturaToFacturaDto(source.getFactura()));

        return pedidoDto;
    }

    @Override
    public Pedido toEntity(PedidoDto source) {
        if (source == null) {
            return null;
        }

        Pedido.PedidoBuilder<?, ?> pedido = Pedido.builder();

        pedido.id(source.getId());
        pedido.eliminado(source.isEliminado());
        pedido.total(source.getTotal());
        pedido.estado(source.getEstado());
        pedido.tipoEnvio(source.getTipoEnvio());
        pedido.domicilio(domicilioCreateDtoToDomicilio(source.getDomicilio()));
        pedido.sucursal(sucursalDtoToSucursal(source.getSucursal()));
        pedido.factura(facturaDtoToFactura(source.getFactura()));
        pedido.detallesPedido(detallePedidoDtoSetToDetallePedidoSet(source.getDetallesPedido()));

        return pedido.build();
    }

    @Override
    public Pedido toEntityCreate(PedidoCreateDto source) {
        if (source == null) {
            return null;
        }

        Pedido.PedidoBuilder<?, ?> pedido = Pedido.builder();

        pedido.id(source.getId());
        pedido.eliminado(source.isEliminado());
        pedido.total(source.getTotal());
        pedido.estado(source.getEstado());
        pedido.tipoEnvio(source.getTipoEnvio());
        pedido.domicilio(domicilioCreateDtoToDomicilio(source.getDomicilio()));
        pedido.sucursal(sucursalService.getById(source.getIdSucursal()));
        pedido.factura(facturaCreateDtoToFactura(source.getFactura()));
        pedido.detallesPedido(detallePedidoCreateDtoListToDetallePedidoSet(source.getDetallesPedido()));

        return pedido.build();
    }


    @Override
    public Pedido toUpdate(Pedido entity, PedidoCreateDto source) {
        if (source == null) {
            return entity;
        }

        entity.setId(source.getId());
        entity.setEliminado(source.isEliminado());
        entity.setTotal(source.getTotal());
        entity.setEstado(source.getEstado());
        entity.setTipoEnvio(source.getTipoEnvio());
        if (source.getDomicilio() != null) {
            if (entity.getDomicilio() == null) {
                entity.setDomicilio(Domicilio.builder().build());
            }
            domicilioCreateDtoToDomicilio1(source.getDomicilio(), entity.getDomicilio());
        } else {
            entity.setDomicilio(null);
        }
        if (source.getFactura() != null) {
            if (entity.getFactura() == null) {
                entity.setFactura(Factura.builder().build());
            }
            facturaCreateDtoToFactura1(source.getFactura(), entity.getFactura());
        } else {
            entity.setFactura(null);
        }
        if (entity.getDetallesPedido() != null) {
            Set<DetallePedido> set = detallePedidoCreateDtoListToDetallePedidoSet(source.getDetallesPedido());
            if (set != null) {
                entity.getDetallesPedido().clear();
                entity.getDetallesPedido().addAll(set);
            } else {
                entity.setDetallesPedido(null);
            }
        } else {
            Set<DetallePedido> set = detallePedidoCreateDtoListToDetallePedidoSet(source.getDetallesPedido());
            if (set != null) {
                entity.setDetallesPedido(set);
            }
        }

        return entity;
    }

    @Override
    public List<PedidoDto> toDTOsList(List<Pedido> source) {
        if (source == null) {
            return null;
        }

        List<PedidoDto> list = new ArrayList<PedidoDto>(source.size());
        for (Pedido pedido : source) {
            list.add(toDTO(pedido));
        }

        return list;
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

    protected DomicilioCreateDto domicilioToDomicilioCreateDto(Domicilio domicilio) {
        if (domicilio == null) {
            return null;
        }

        DomicilioCreateDto domicilioCreateDto = new DomicilioCreateDto();

        domicilioCreateDto.setCalle(domicilio.getCalle());
        domicilioCreateDto.setNumero(domicilio.getNumero());
        domicilioCreateDto.setCp(domicilio.getCp());
        domicilioCreateDto.setPiso(domicilio.getPiso());
        domicilioCreateDto.setNroDpto(domicilio.getNroDpto());

        return domicilioCreateDto;
    }

    protected PaisDto paisToPaisDto(Pais pais) {
        if (pais == null) {
            return null;
        }

        PaisDto paisDto = new PaisDto();

        if (pais.isEliminado() != null) {
            paisDto.setEliminado(pais.isEliminado());
        }
        paisDto.setId(pais.getId());
        paisDto.setNombre(pais.getNombre());

        return paisDto;
    }

    protected ProvinciaDto provinciaToProvinciaDto(Provincia provincia) {
        if (provincia == null) {
            return null;
        }

        ProvinciaDto provinciaDto = new ProvinciaDto();

        provinciaDto.setId(provincia.getId());
        if (provincia.isEliminado() != null) {
            provinciaDto.setEliminado(provincia.isEliminado());
        }
        provinciaDto.setNombre(provincia.getNombre());
        provinciaDto.setPais(paisToPaisDto(provincia.getPais()));

        return provinciaDto;
    }

    protected LocalidadDto localidadToLocalidadDto(Localidad localidad) {
        if (localidad == null) {
            return null;
        }

        LocalidadDto localidadDto = new LocalidadDto();

        localidadDto.setId(localidad.getId());
        if (localidad.isEliminado() != null) {
            localidadDto.setEliminado(localidad.isEliminado());
        }
        localidadDto.setNombre(localidad.getNombre());
        localidadDto.setProvincia(provinciaToProvinciaDto(localidad.getProvincia()));

        return localidadDto;
    }

    protected DomicilioDto domicilioToDomicilioDto(Domicilio domicilio) {
        if (domicilio == null) {
            return null;
        }

        DomicilioDto domicilioDto = new DomicilioDto();

        domicilioDto.setId(domicilio.getId());
        if (domicilio.isEliminado() != null) {
            domicilioDto.setEliminado(domicilio.isEliminado());
        }
        domicilioDto.setCalle(domicilio.getCalle());
        domicilioDto.setNumero(domicilio.getNumero());
        domicilioDto.setCp(domicilio.getCp());
        domicilioDto.setPiso(domicilio.getPiso());
        domicilioDto.setNroDpto(domicilio.getNroDpto());
        domicilioDto.setLocalidad(localidadToLocalidadDto(domicilio.getLocalidad()));

        return domicilioDto;
    }

    protected ImagenEmpresaDto imagenEmpresaToImagenEmpresaDto(ImagenEmpresa imagenEmpresa) {
        if (imagenEmpresa == null) {
            return null;
        }

        ImagenEmpresaDto imagenEmpresaDto = new ImagenEmpresaDto();

        imagenEmpresaDto.setName(imagenEmpresa.getName());
        imagenEmpresaDto.setUrl(imagenEmpresa.getUrl());

        return imagenEmpresaDto;
    }

    protected List<ImagenEmpresaDto> imagenEmpresaSetToImagenEmpresaDtoList(Set<ImagenEmpresa> set) {
        if (set == null) {
            return null;
        }

        List<ImagenEmpresaDto> list = new ArrayList<ImagenEmpresaDto>(set.size());
        for (ImagenEmpresa imagenEmpresa : set) {
            list.add(imagenEmpresaToImagenEmpresaDto(imagenEmpresa));
        }

        return list;
    }

    protected EmpresaDto empresaToEmpresaDto(Empresa empresa) {
        if (empresa == null) {
            return null;
        }

        EmpresaDto empresaDto = new EmpresaDto();

        empresaDto.setId(empresa.getId());
        if (empresa.isEliminado() != null) {
            empresaDto.setEliminado(empresa.isEliminado());
        }
        empresaDto.setNombre(empresa.getNombre());
        empresaDto.setRazonSocial(empresa.getRazonSocial());
        empresaDto.setCuil(empresa.getCuil());
        empresaDto.setImagenes(imagenEmpresaSetToImagenEmpresaDtoList(empresa.getImagenes()));

        return empresaDto;
    }

    protected ImagenSucursalDto imagenSucursalToImagenSucursalDto(ImagenSucursal imagenSucursal) {
        if (imagenSucursal == null) {
            return null;
        }

        ImagenSucursalDto imagenSucursalDto = new ImagenSucursalDto();

        imagenSucursalDto.setName(imagenSucursal.getName());
        imagenSucursalDto.setUrl(imagenSucursal.getUrl());

        return imagenSucursalDto;
    }

    protected List<ImagenSucursalDto> imagenSucursalSetToImagenSucursalDtoList(Set<ImagenSucursal> set) {
        if (set == null) {
            return null;
        }

        List<ImagenSucursalDto> list = new ArrayList<ImagenSucursalDto>(set.size());
        for (ImagenSucursal imagenSucursal : set) {
            list.add(imagenSucursalToImagenSucursalDto(imagenSucursal));
        }

        return list;
    }

    protected SucursalDto sucursalToSucursalDto(Sucursal sucursal) {
        if (sucursal == null) {
            return null;
        }

        SucursalDto sucursalDto = new SucursalDto();

        sucursalDto.setId(sucursal.getId());
        if (sucursal.isEliminado() != null) {
            sucursalDto.setEliminado(sucursal.isEliminado());
        }
        sucursalDto.setNombre(sucursal.getNombre());
        sucursalDto.setHorarioApertura(sucursal.getHorarioApertura());
        sucursalDto.setHorarioCierre(sucursal.getHorarioCierre());
        sucursalDto.setEsCasaMatriz(sucursal.getEsCasaMatriz());
        sucursalDto.setDomicilio(domicilioToDomicilioDto(sucursal.getDomicilio()));
        sucursalDto.setEmpresa(empresaToEmpresaDto(sucursal.getEmpresa()));
        sucursalDto.setImagenes(imagenSucursalSetToImagenSucursalDtoList(sucursal.getImagenes()));

        return sucursalDto;
    }

    protected FacturaDto facturaToFacturaDto(Factura factura) {
        if (factura == null) {
            return null;
        }

        FacturaDto facturaDto = new FacturaDto();

        facturaDto.setId(factura.getId());
        if (factura.isEliminado() != null) {
            facturaDto.setEliminado(factura.isEliminado());
        }
        facturaDto.setFechaFcturacion(factura.getFechaFcturacion());
        facturaDto.setMpPaymentId(factura.getMpPaymentId());
        facturaDto.setMpMerchantOrderId(factura.getMpMerchantOrderId());
        facturaDto.setMpPreferenceId(factura.getMpPreferenceId());
        facturaDto.setMpPaymentType(factura.getMpPaymentType());
        facturaDto.setFormaPago(factura.getFormaPago());
        facturaDto.setTotalVenta(factura.getTotalVenta());

        return facturaDto;
    }

    protected Domicilio domicilioCreateDtoToDomicilio(DomicilioCreateDto domicilioCreateDto) {
        if (domicilioCreateDto == null) {
            return null;
        }

        Domicilio.DomicilioBuilder<?, ?> domicilio = Domicilio.builder();

        domicilio.calle(domicilioCreateDto.getCalle());
        domicilio.numero(domicilioCreateDto.getNumero());
        domicilio.cp(domicilioCreateDto.getCp());
        domicilio.piso(domicilioCreateDto.getPiso());
        domicilio.nroDpto(domicilioCreateDto.getNroDpto());

        return domicilio.build();
    }

    protected Pais paisDtoToPais(PaisDto paisDto) {
        if (paisDto == null) {
            return null;
        }

        Pais.PaisBuilder<?, ?> pais = Pais.builder();

        pais.id(paisDto.getId());
        pais.eliminado(paisDto.isEliminado());
        pais.nombre(paisDto.getNombre());

        return pais.build();
    }

    protected Provincia provinciaDtoToProvincia(ProvinciaDto provinciaDto) {
        if (provinciaDto == null) {
            return null;
        }

        Provincia.ProvinciaBuilder<?, ?> provincia = Provincia.builder();

        provincia.id(provinciaDto.getId());
        provincia.eliminado(provinciaDto.isEliminado());
        provincia.nombre(provinciaDto.getNombre());
        provincia.pais(paisDtoToPais(provinciaDto.getPais()));

        return provincia.build();
    }

    protected Localidad localidadDtoToLocalidad(LocalidadDto localidadDto) {
        if (localidadDto == null) {
            return null;
        }

        Localidad.LocalidadBuilder<?, ?> localidad = Localidad.builder();

        localidad.id(localidadDto.getId());
        localidad.eliminado(localidadDto.isEliminado());
        localidad.nombre(localidadDto.getNombre());
        localidad.provincia(provinciaDtoToProvincia(localidadDto.getProvincia()));

        return localidad.build();
    }

    protected Domicilio domicilioDtoToDomicilio(DomicilioDto domicilioDto) {
        if (domicilioDto == null) {
            return null;
        }

        Domicilio.DomicilioBuilder<?, ?> domicilio = Domicilio.builder();

        domicilio.id(domicilioDto.getId());
        domicilio.eliminado(domicilioDto.isEliminado());
        domicilio.calle(domicilioDto.getCalle());
        domicilio.numero(domicilioDto.getNumero());
        domicilio.cp(domicilioDto.getCp());
        domicilio.piso(domicilioDto.getPiso());
        domicilio.nroDpto(domicilioDto.getNroDpto());
        domicilio.localidad(localidadDtoToLocalidad(domicilioDto.getLocalidad()));

        return domicilio.build();
    }

    protected ImagenEmpresa imagenEmpresaDtoToImagenEmpresa(ImagenEmpresaDto imagenEmpresaDto) {
        if (imagenEmpresaDto == null) {
            return null;
        }

        ImagenEmpresa.ImagenEmpresaBuilder<?, ?> imagenEmpresa = ImagenEmpresa.builder();

        imagenEmpresa.name(imagenEmpresaDto.getName());
        imagenEmpresa.url(imagenEmpresaDto.getUrl());

        return imagenEmpresa.build();
    }

    protected Set<ImagenEmpresa> imagenEmpresaDtoListToImagenEmpresaSet(List<ImagenEmpresaDto> list) {
        if (list == null) {
            return null;
        }

        Set<ImagenEmpresa> set = new LinkedHashSet<ImagenEmpresa>(Math.max((int) (list.size() / .75f) + 1, 16));
        for (ImagenEmpresaDto imagenEmpresaDto : list) {
            set.add(imagenEmpresaDtoToImagenEmpresa(imagenEmpresaDto));
        }

        return set;
    }

    protected Empresa empresaDtoToEmpresa(EmpresaDto empresaDto) {
        if (empresaDto == null) {
            return null;
        }

        Empresa.EmpresaBuilder<?, ?> empresa = Empresa.builder();

        empresa.id(empresaDto.getId());
        empresa.eliminado(empresaDto.isEliminado());
        empresa.nombre(empresaDto.getNombre());
        empresa.razonSocial(empresaDto.getRazonSocial());
        empresa.cuil(empresaDto.getCuil());
        empresa.imagenes(imagenEmpresaDtoListToImagenEmpresaSet(empresaDto.getImagenes()));

        return empresa.build();
    }

    protected ImagenSucursal imagenSucursalDtoToImagenSucursal(ImagenSucursalDto imagenSucursalDto) {
        if (imagenSucursalDto == null) {
            return null;
        }

        ImagenSucursal.ImagenSucursalBuilder<?, ?> imagenSucursal = ImagenSucursal.builder();

        imagenSucursal.name(imagenSucursalDto.getName());
        imagenSucursal.url(imagenSucursalDto.getUrl());

        return imagenSucursal.build();
    }

    protected Set<ImagenSucursal> imagenSucursalDtoListToImagenSucursalSet(List<ImagenSucursalDto> list) {
        if (list == null) {
            return null;
        }

        Set<ImagenSucursal> set = new LinkedHashSet<ImagenSucursal>(Math.max((int) (list.size() / .75f) + 1, 16));
        for (ImagenSucursalDto imagenSucursalDto : list) {
            set.add(imagenSucursalDtoToImagenSucursal(imagenSucursalDto));
        }

        return set;
    }

    protected Sucursal sucursalDtoToSucursal(SucursalDto sucursalDto) {
        if (sucursalDto == null) {
            return null;
        }

        Sucursal.SucursalBuilder<?, ?> sucursal = Sucursal.builder();

        sucursal.id(sucursalDto.getId());
        sucursal.eliminado(sucursalDto.isEliminado());
        sucursal.nombre(sucursalDto.getNombre());
        sucursal.horarioApertura(sucursalDto.getHorarioApertura());
        sucursal.horarioCierre(sucursalDto.getHorarioCierre());
        sucursal.esCasaMatriz(sucursalDto.getEsCasaMatriz());
        sucursal.domicilio(domicilioDtoToDomicilio(sucursalDto.getDomicilio()));
        sucursal.empresa(empresaDtoToEmpresa(sucursalDto.getEmpresa()));
        sucursal.imagenes(imagenSucursalDtoListToImagenSucursalSet(sucursalDto.getImagenes()));

        return sucursal.build();
    }

    protected Factura facturaDtoToFactura(FacturaDto facturaDto) {
        if (facturaDto == null) {
            return null;
        }

        Factura.FacturaBuilder<?, ?> factura = Factura.builder();

        factura.id(facturaDto.getId());
        factura.eliminado(facturaDto.isEliminado());
        factura.fechaFcturacion(facturaDto.getFechaFcturacion());
        factura.mpPaymentId(facturaDto.getMpPaymentId());
        factura.mpMerchantOrderId(facturaDto.getMpMerchantOrderId());
        factura.mpPreferenceId(facturaDto.getMpPreferenceId());
        factura.mpPaymentType(facturaDto.getMpPaymentType());
        factura.formaPago(facturaDto.getFormaPago());
        factura.totalVenta(facturaDto.getTotalVenta());

        return factura.build();
    }

    protected DetallePedido detallePedidoDtoToDetallePedido(DetallePedidoDto detallePedidoDto) {
        if (detallePedidoDto == null) {
            return null;
        }

        DetallePedido.DetallePedidoBuilder<?, ?> detallePedido = DetallePedido.builder();

        detallePedido.id(detallePedidoDto.getId());
        detallePedido.eliminado(detallePedidoDto.isEliminado());
        detallePedido.cantidad(detallePedidoDto.getCantidad());
        detallePedido.subTotal(detallePedidoDto.getSubTotal());

        return detallePedido.build();
    }

    protected Set<DetallePedido> detallePedidoDtoSetToDetallePedidoSet(Set<DetallePedidoDto> set) {
        if (set == null) {
            return null;
        }

        Set<DetallePedido> set1 = new LinkedHashSet<DetallePedido>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (DetallePedidoDto detallePedidoDto : set) {
            set1.add(detallePedidoDtoToDetallePedido(detallePedidoDto));
        }

        return set1;
    }

    protected Factura facturaCreateDtoToFactura(FacturaCreateDto facturaCreateDto) {
        if (facturaCreateDto == null) {
            return null;
        }

        Factura.FacturaBuilder<?, ?> factura = Factura.builder();

        factura.id(facturaCreateDto.getId());
        factura.eliminado(facturaCreateDto.isEliminado());
        factura.fechaFcturacion(facturaCreateDto.getFechaFcturacion());
        factura.mpPaymentId(facturaCreateDto.getMpPaymentId());
        factura.mpMerchantOrderId(facturaCreateDto.getMpMerchantOrderId());
        factura.mpPreferenceId(facturaCreateDto.getMpPreferenceId());
        factura.mpPaymentType(facturaCreateDto.getMpPaymentType());
        factura.formaPago(facturaCreateDto.getFormaPago());
        factura.totalVenta(facturaCreateDto.getTotalVenta());

        return factura.build();
    }

    protected DetallePedido detallePedidoCreateDtoToDetallePedido(DetallePedidoCreateDto detallePedidoCreateDto) {
        if (detallePedidoCreateDto == null) {
            return null;
        }

        DetallePedido.DetallePedidoBuilder<?, ?> detallePedido = DetallePedido.builder();

        detallePedido.id(detallePedidoCreateDto.getId());
        detallePedido.eliminado(detallePedidoCreateDto.isEliminado());
        detallePedido.cantidad(detallePedidoCreateDto.getCantidad());
        detallePedido.subTotal(detallePedidoCreateDto.getSubTotal());

        // Asigna el Articulo al DetallePedido
        Articulo articulo = articuloRepository.getById(detallePedidoCreateDto.getIdArticulo());
        detallePedido.articulo(articulo);

        return detallePedido.build();
    }

    protected Set<DetallePedido> detallePedidoCreateDtoListToDetallePedidoSet(List<DetallePedidoCreateDto> list) {
        if (list == null) {
            return null;
        }

        Set<DetallePedido> set = new LinkedHashSet<DetallePedido>(Math.max((int) (list.size() / .75f) + 1, 16));
        for (DetallePedidoCreateDto detallePedidoCreateDto : list) {
            set.add(detallePedidoCreateDtoToDetallePedido(detallePedidoCreateDto));
        }

        return set;
    }


    protected void domicilioCreateDtoToDomicilio1(DomicilioCreateDto domicilioCreateDto, Domicilio mappingTarget) {
        if (domicilioCreateDto == null) {
            return;
        }

        mappingTarget.setCalle(domicilioCreateDto.getCalle());
        mappingTarget.setNumero(domicilioCreateDto.getNumero());
        mappingTarget.setCp(domicilioCreateDto.getCp());
        mappingTarget.setPiso(domicilioCreateDto.getPiso());
        mappingTarget.setNroDpto(domicilioCreateDto.getNroDpto());
    }

    protected void paisDtoToPais1(PaisDto paisDto, Pais mappingTarget) {
        if (paisDto == null) {
            return;
        }

        mappingTarget.setId(paisDto.getId());
        mappingTarget.setEliminado(paisDto.isEliminado());
        mappingTarget.setNombre(paisDto.getNombre());
    }

    protected void provinciaDtoToProvincia1(ProvinciaDto provinciaDto, Provincia mappingTarget) {
        if (provinciaDto == null) {
            return;
        }

        mappingTarget.setId(provinciaDto.getId());
        mappingTarget.setEliminado(provinciaDto.isEliminado());
        mappingTarget.setNombre(provinciaDto.getNombre());
        if (provinciaDto.getPais() != null) {
            if (mappingTarget.getPais() == null) {
                mappingTarget.setPais(Pais.builder().build());
            }
            paisDtoToPais1(provinciaDto.getPais(), mappingTarget.getPais());
        } else {
            mappingTarget.setPais(null);
        }
    }

    protected void localidadDtoToLocalidad1(LocalidadDto localidadDto, Localidad mappingTarget) {
        if (localidadDto == null) {
            return;
        }

        mappingTarget.setId(localidadDto.getId());
        mappingTarget.setEliminado(localidadDto.isEliminado());
        mappingTarget.setNombre(localidadDto.getNombre());
        if (localidadDto.getProvincia() != null) {
            if (mappingTarget.getProvincia() == null) {
                mappingTarget.setProvincia(Provincia.builder().build());
            }
            provinciaDtoToProvincia1(localidadDto.getProvincia(), mappingTarget.getProvincia());
        } else {
            mappingTarget.setProvincia(null);
        }
    }

    protected void domicilioDtoToDomicilio1(DomicilioDto domicilioDto, Domicilio mappingTarget) {
        if (domicilioDto == null) {
            return;
        }

        mappingTarget.setId(domicilioDto.getId());
        mappingTarget.setEliminado(domicilioDto.isEliminado());
        mappingTarget.setCalle(domicilioDto.getCalle());
        mappingTarget.setNumero(domicilioDto.getNumero());
        mappingTarget.setCp(domicilioDto.getCp());
        mappingTarget.setPiso(domicilioDto.getPiso());
        mappingTarget.setNroDpto(domicilioDto.getNroDpto());
        if (domicilioDto.getLocalidad() != null) {
            if (mappingTarget.getLocalidad() == null) {
                mappingTarget.setLocalidad(Localidad.builder().build());
            }
            localidadDtoToLocalidad1(domicilioDto.getLocalidad(), mappingTarget.getLocalidad());
        } else {
            mappingTarget.setLocalidad(null);
        }
    }

    protected void empresaDtoToEmpresa1(EmpresaDto empresaDto, Empresa mappingTarget) {
        if (empresaDto == null) {
            return;
        }

        mappingTarget.setId(empresaDto.getId());
        mappingTarget.setEliminado(empresaDto.isEliminado());
        mappingTarget.setNombre(empresaDto.getNombre());
        mappingTarget.setRazonSocial(empresaDto.getRazonSocial());
        mappingTarget.setCuil(empresaDto.getCuil());
        if (mappingTarget.getImagenes() != null) {
            Set<ImagenEmpresa> set = imagenEmpresaDtoListToImagenEmpresaSet(empresaDto.getImagenes());
            if (set != null) {
                mappingTarget.getImagenes().clear();
                mappingTarget.getImagenes().addAll(set);
            } else {
                mappingTarget.setImagenes(null);
            }
        } else {
            Set<ImagenEmpresa> set = imagenEmpresaDtoListToImagenEmpresaSet(empresaDto.getImagenes());
            if (set != null) {
                mappingTarget.setImagenes(set);
            }
        }
    }

    protected void sucursalDtoToSucursal1(SucursalDto sucursalDto, Sucursal mappingTarget) {
        if (sucursalDto == null) {
            return;
        }

        mappingTarget.setId(sucursalDto.getId());
        mappingTarget.setEliminado(sucursalDto.isEliminado());
        mappingTarget.setNombre(sucursalDto.getNombre());
        mappingTarget.setHorarioApertura(sucursalDto.getHorarioApertura());
        mappingTarget.setHorarioCierre(sucursalDto.getHorarioCierre());
        mappingTarget.setEsCasaMatriz(sucursalDto.getEsCasaMatriz());
        if (sucursalDto.getDomicilio() != null) {
            if (mappingTarget.getDomicilio() == null) {
                mappingTarget.setDomicilio(Domicilio.builder().build());
            }
            domicilioDtoToDomicilio1(sucursalDto.getDomicilio(), mappingTarget.getDomicilio());
        } else {
            mappingTarget.setDomicilio(null);
        }
        if (sucursalDto.getEmpresa() != null) {
            if (mappingTarget.getEmpresa() == null) {
                mappingTarget.setEmpresa(Empresa.builder().build());
            }
            empresaDtoToEmpresa1(sucursalDto.getEmpresa(), mappingTarget.getEmpresa());
        } else {
            mappingTarget.setEmpresa(null);
        }
        if (mappingTarget.getImagenes() != null) {
            Set<ImagenSucursal> set = imagenSucursalDtoListToImagenSucursalSet(sucursalDto.getImagenes());
            if (set != null) {
                mappingTarget.getImagenes().clear();
                mappingTarget.getImagenes().addAll(set);
            } else {
                mappingTarget.setImagenes(null);
            }
        } else {
            Set<ImagenSucursal> set = imagenSucursalDtoListToImagenSucursalSet(sucursalDto.getImagenes());
            if (set != null) {
                mappingTarget.setImagenes(set);
            }
        }
    }

    protected void facturaCreateDtoToFactura1(FacturaCreateDto facturaCreateDto, Factura mappingTarget) {
        if (facturaCreateDto == null) {
            return;
        }

        mappingTarget.setId(facturaCreateDto.getId());
        mappingTarget.setEliminado(facturaCreateDto.isEliminado());
        mappingTarget.setFechaFcturacion(facturaCreateDto.getFechaFcturacion());
        mappingTarget.setMpPaymentId(facturaCreateDto.getMpPaymentId());
        mappingTarget.setMpMerchantOrderId(facturaCreateDto.getMpMerchantOrderId());
        mappingTarget.setMpPreferenceId(facturaCreateDto.getMpPreferenceId());
        mappingTarget.setMpPaymentType(facturaCreateDto.getMpPaymentType());
        mappingTarget.setFormaPago(facturaCreateDto.getFormaPago());
        mappingTarget.setTotalVenta(facturaCreateDto.getTotalVenta());
    }
}
