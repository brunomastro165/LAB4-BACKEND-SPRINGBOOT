package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.mapper.PedidoMapper;
import com.example.buensaborback.business.services.ArticuloInsumoService;
import com.example.buensaborback.business.services.ArticuloManufacturadoService;
import com.example.buensaborback.business.services.PedidoService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.dto.Pedido.PedidoDto;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.enums.Estado;
import com.example.buensaborback.repositories.ArticuloRepository;
import com.example.buensaborback.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido, Long> implements PedidoService {
    @Autowired
    ArticuloRepository articuloRepository;
    @Autowired
    ArticuloInsumoService articuloInsumoService;
    @Autowired
    ArticuloManufacturadoService articuloManufacturadoService;
    @Autowired
    PedidoMapper pedidoMapper;
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Transactional
    @Override
    public Pedido create(Pedido request) {
        System.out.println("Estoy en service");
        request = calcularSubtotales(request);
        // Configurar el estado del pedido
        request.setHoraEstimadaFinalizacion(LocalTime.now());
        request.setEstado(actualizarStocks(request) ? Estado.RECHAZADO : Estado.PENDIENTE);
        request.setFechaPedido(LocalDate.now());
        request.getFactura().setFechaFcturacion(LocalDate.now());
        request.getFactura().setTotalVenta(request.getTotal());
        var newEntity = baseRepository.save(request);
        return newEntity;
    }

    // Funcion para calcular los totales
    private Pedido calcularSubtotales(Pedido request) {
        double totalCosto = 0;
        double total = 0;
        for (DetallePedido detalle : request.getDetallesPedido()) {
            if (detalle.getPromocion() != null) {
                detalle.setSubTotal(detalle.getPromocion().getPrecioPromocional() * detalle.getCantidad());
                total += detalle.getSubTotal();
                totalCosto += totalCostoPromocion(detalle.getPromocion()) * detalle.getCantidad();
            } else {
                detalle.setSubTotal(detalle.getArticulo().getPrecioVenta() * detalle.getCantidad());
                total += detalle.getSubTotal();
                totalCosto += totalCostoArticulo(detalle.getArticulo()) * detalle.getCantidad();
            }
        }
        request.setTotal(total);
        request.setTotalCosto(totalCosto);
        return request;
    }

    private double totalCostoArticulo(Articulo articulo) {
        double totalCosto = 0;
        if (articulo instanceof ArticuloInsumo)
            totalCosto = ((ArticuloInsumo) articulo).getPrecioCompra();
        if (articulo instanceof ArticuloManufacturado) {
            for (ArticuloManufacturadoDetalle detalle :
                    ((ArticuloManufacturado) articulo).getArticuloManufacturadoDetalles()) {
                totalCosto += totalCostoArticulo(detalle.getArticuloInsumo()) * detalle.getCantidad();
            }
        }
        return totalCosto;
    }

    private double totalCostoPromocion(Promocion promocion) {
        double totalCosto = 0;
        for (PromocionDetalle detalle :
                promocion.getDetalles()) {
            totalCosto += totalCostoArticulo(detalle.getArticulo()) * detalle.getCantidad();
        }
        return totalCosto;
    }

    //Funcion principal para llamar a la comprabacion de stock resta de este
    private boolean actualizarStocks(Pedido request) {
        if (!comprobarStock(request)) {
            restarStock(request);
            return false;
        }
        return true;
    }


    //Funciones para restar stock
    private void restarStock(Pedido request) {
        for (DetallePedido detalle : request.getDetallesPedido()) {
            if (detalle.getPromocion() == null) {
                restarStockArticulos(detalle.getArticulo(), detalle.getCantidad());
            } else
                restarStockPromociones(detalle.getPromocion(), detalle.getCantidad());
        }
    }

    private void restarStockArticulos(Articulo articulo, Integer cantidad) {
        if (articulo instanceof ArticuloInsumo) {
            ArticuloInsumo articuloInsumo = articuloInsumoService.getById(articulo.getId());
            articuloInsumo.setStockActual(articuloInsumo.getStockActual() - cantidad);
            articuloInsumoService.update(articuloInsumo, articulo.getId());
        }
        if (articulo instanceof ArticuloManufacturado) {
            for (ArticuloManufacturadoDetalle detalle :
                    ((ArticuloManufacturado) articulo).getArticuloManufacturadoDetalles()) {
                restarStockArticulos(detalle.getArticuloInsumo(), detalle.getCantidad() * cantidad);
            }
        }
    }

    private void restarStockPromociones(Promocion promocion, Integer cantidad) {
        for (PromocionDetalle detalle :
                promocion.getDetalles()) {
            restarStockArticulos(detalle.getArticulo(), detalle.getCantidad() * cantidad);

        }
    }


    //Funciones para comprobar stock
    private boolean comprobarStock(Pedido request) {
        for (DetallePedido detalle : request.getDetallesPedido()) {
            if (detalle.getPromocion() == null) {
                if (comprobarArticulos(detalle.getArticulo(), detalle.getCantidad()))
                    return true;
            } else if (comprobarPromociones(detalle.getPromocion(), detalle.getCantidad()))
                return true;
        }
        return false;
    }

    private boolean comprobarArticulos(Articulo articulo, Integer cantidad) {
        if (articulo instanceof ArticuloInsumo) {
            if (((ArticuloInsumo) articulo).getStockActual() - cantidad < ((ArticuloInsumo) articulo).getStockMinimo())
                return true;
        }
        if (articulo instanceof ArticuloManufacturado) {
            for (ArticuloManufacturadoDetalle detalle :
                    ((ArticuloManufacturado) articulo).getArticuloManufacturadoDetalles()) {
                if (comprobarArticulos(detalle.getArticuloInsumo(), detalle.getCantidad() * cantidad))
                    return true;
            }
        }
        return false;
    }

    private boolean comprobarPromociones(Promocion promocion, Integer cantidad) {
        for (PromocionDetalle detalle :
                promocion.getDetalles()) {
            if (comprobarArticulos(detalle.getArticulo(), detalle.getCantidad() * cantidad))
                return true;
        }
        return false;
    }

    public List<PedidoDto> getPorFecha(Date fechaInicio, Date fechaFin, Long idSucursal) {
        LocalDate inicio = fechaInicio.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate fin = fechaFin.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        List<PedidoDto> pedidos = pedidoMapper.toDTOsList(getAll());
        return pedidos.stream()
                .filter(pedido -> pedido.getFechaPedido().isBefore(fin)
                        && pedido.getFechaPedido().isAfter(inicio)
                        && pedido.getSucursal().getId().equals(idSucursal))
                .collect(Collectors.toList());
    }

    public Pedido asignarEmpleado(Long idEmpleado, Long idPedido) {
        Pedido pedido = getById(idPedido);
        pedido.setEmpleado(empleadoRepository.getById(idEmpleado));
        return update(pedido, idPedido);
    }
    public Pedido cancelar(Long id){
        Pedido pedido = getById(id);
        if(pedido.getEstado() == Estado.PENDIENTE){
            pedido.setEstado(Estado.CANCELADO);
            sumarStock(pedido);
            return update(pedido,id);
        }else
            return getById(id);


    }
    //Funciones para sumar stock
    private void sumarStock(Pedido request) {
        for (DetallePedido detalle : request.getDetallesPedido()) {
            if (detalle.getPromocion() == null) {
                sumarStockArticulos(detalle.getArticulo(), detalle.getCantidad());
            } else
                sumarStockPromociones(detalle.getPromocion(), detalle.getCantidad());
        }
    }

    private void sumarStockArticulos(Articulo articulo, Integer cantidad) {
        if (articulo instanceof ArticuloInsumo) {
            ArticuloInsumo articuloInsumo = articuloInsumoService.getById(articulo.getId());
            articuloInsumo.setStockActual(articuloInsumo.getStockActual() + cantidad);
            articuloInsumoService.update(articuloInsumo, articulo.getId());
        }
        if (articulo instanceof ArticuloManufacturado) {
            for (ArticuloManufacturadoDetalle detalle :
                    ((ArticuloManufacturado) articulo).getArticuloManufacturadoDetalles()) {
                sumarStockArticulos(detalle.getArticuloInsumo(), detalle.getCantidad() * cantidad);
            }
        }
    }

    private void sumarStockPromociones(Promocion promocion, Integer cantidad) {
        for (PromocionDetalle detalle :
                promocion.getDetalles()) {
            sumarStockArticulos(detalle.getArticulo(), detalle.getCantidad() * cantidad);
        }
    }



}
