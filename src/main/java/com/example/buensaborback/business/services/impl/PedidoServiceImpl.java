package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.ArticuloInsumoService;
import com.example.buensaborback.business.services.ArticuloManufacturadoService;
import com.example.buensaborback.business.services.PedidoService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoCreateDto;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.enums.Estado;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido, Long> implements PedidoService {
    @Autowired
    ArticuloRepository articuloRepository;
    @Autowired
    ArticuloInsumoService articuloInsumoService;
    @Autowired
    ArticuloManufacturadoService articuloManufacturadoService;
    @Transactional
    @Override
    public Pedido create(Pedido request) {
        System.out.println("Estoy en service");
        System.out.println(request);

        boolean denegar = calcularSubtotalesYVerificarCondiciones(request);
        if (!denegar) {
            denegar = actualizarStocks(request);
        }

        // Configurar el estado del pedido
        request.setHoraEstimadaFinalizacion(LocalTime.now());
        request.setEstado(denegar ? Estado.RECHAZADO : Estado.PENDIENTE);
        request.setFechaPedido(LocalDate.now());

        var newEntity = baseRepository.save(request);
        return newEntity;
    }

    private boolean calcularSubtotalesYVerificarCondiciones(Pedido request) {
        boolean denegar = false;
        double costoTotal = 0;
        double total = 0;

        for (DetallePedido detalle : request.getDetallesPedido()) {
            Articulo articulo = articuloRepository.getById(detalle.getArticulo().getId());

            // Calcular y establecer el subtotal
            double subTotal = articulo.getPrecioVenta() * detalle.getCantidad();
            detalle.setSubTotal(subTotal);

            // Acumular el total
            total += detalle.getSubTotal();

            // Verificar condiciones y calcular costo total
            if (articulo instanceof ArticuloInsumo) {
                ArticuloInsumo articuloInsumo = (ArticuloInsumo) articulo;
                costoTotal += articuloInsumo.getPrecioCompra();
                if (articuloInsumo.getStockActual() <= articuloInsumo.getStockMinimo()) {
                    denegar = true;
                }
            } else if (articulo instanceof ArticuloManufacturado) {
                ArticuloManufacturado articuloManufacturado = (ArticuloManufacturado) articulo;
                for (ArticuloManufacturadoDetalle detalle1 : articuloManufacturado.getArticuloManufacturadoDetalles()) {
                    if (detalle1.getArticuloInsumo().getStockActual() <= detalle1.getArticuloInsumo().getStockMinimo()) {
                        denegar = true;
                    }
                    costoTotal += detalle1.getArticuloInsumo().getPrecioVenta() * detalle.getCantidad();
                }
            }
        }

        request.setTotal(total);
        request.setTotalCosto(costoTotal);

        return denegar;
    }

    private boolean actualizarStocks(Pedido request) {
        boolean denegar = false;

        for (DetallePedido detalle : request.getDetallesPedido()) {
            Articulo articulo = articuloRepository.getById(detalle.getArticulo().getId());

            if (articulo instanceof ArticuloInsumo) {
                ArticuloInsumo articuloInsumo = (ArticuloInsumo) articulo;
                if (articuloInsumo.getStockActual() - detalle.getCantidad() <= articuloInsumo.getStockMinimo()) {
                    denegar = true;
                    break;
                }
                articuloInsumo.setStockActual(articuloInsumo.getStockActual() - detalle.getCantidad());
                articuloInsumoService.update(articuloInsumo,articuloInsumo.getId());
            } else if (articulo instanceof ArticuloManufacturado) {
                ArticuloManufacturado articuloManufacturado = (ArticuloManufacturado) articulo;
                for (ArticuloManufacturadoDetalle detalle1 : articuloManufacturado.getArticuloManufacturadoDetalles()) {
                    ArticuloInsumo articuloInsumo = detalle1.getArticuloInsumo();
                    if (articuloInsumo.getStockActual() - detalle1.getCantidad() * detalle.getCantidad() <= articuloInsumo.getStockMinimo()) {
                        denegar = true;
                        break;
                    }
                    articuloInsumo.setStockActual(articuloInsumo.getStockActual() - detalle1.getCantidad() * detalle.getCantidad());
                    articuloInsumoService.update(articuloInsumo,articuloInsumo.getId());
                }
                if (denegar) break;
            }
        }

        return denegar;
    }


}
