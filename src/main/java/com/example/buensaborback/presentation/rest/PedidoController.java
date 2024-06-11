package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.ClienteFacade;
import com.example.buensaborback.business.facade.impl.PedidoFacadeImpl;
import com.example.buensaborback.business.mapper.ArticuloInsumoMapper;
import com.example.buensaborback.business.mapper.ArticuloManufacturadoMapper;
import com.example.buensaborback.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoCreateDto;
import com.example.buensaborback.domain.dto.DetallePedido.DetallePedidoDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoCreateDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoDto;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.enums.Estado;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import com.example.buensaborback.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/pedido")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoDto, PedidoCreateDto, PedidoCreateDto, Long, PedidoFacadeImpl> {
    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoController(PedidoFacadeImpl facade) {
        super(facade);
    }
    @PreAuthorize("hasRole('ADMIN') || hasRole('COCINERO') || hasRole('CAJERO')|| hasRole('DELIVERY')")
    @GetMapping("/getPorCliente/{id}")
    public ResponseEntity<List<PedidoDto>> getPorCliente(@PathVariable Long id) {
        // Obtén todos los pedidos con el facade
        List<PedidoDto> pedidos = facade.getAll();
        List<PedidoDto> filteredPedidos = pedidos.stream()
                .filter(a -> a.getCliente() != null && a.getCliente().getId().equals(id)
                        && !a.isEliminado())
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredPedidos);
    }


    @PreAuthorize("isAuthenticated()")
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> getById(@PathVariable Long id) {
        //obtengo el pedido con el facade aca los articulos son nulos
        PedidoDto pedido = facade.getById(id);
        return ResponseEntity.ok(pedido);
    }
    @PreAuthorize("hasRole('ADMIN') || hasRole('COCINERO') || hasRole('CAJERO')|| hasRole('DELIVERY')")
    @PutMapping("/cambiarEstado/{id}")
    public ResponseEntity<?> cambiarEstado(@RequestBody Estado entity, @PathVariable Long id) {
        try {
            Pedido pedido = pedidoRepository.getById(id);
            pedido.setEstado(entity);
            pedidoRepository.save(pedido);
            return  ResponseEntity.ok(facade.getById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El pedido no ha podido cambiarse");
        }

    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping()
    public ResponseEntity<PedidoDto> create(@RequestBody PedidoCreateDto entity) {
        try {
            entity.getFactura().setFormaPago(FormaPago.EFECTIVO);
            entity.getFactura().setFechaFcturacion(LocalDate.now());
            entity.getFactura().setTotalVenta(entity.getTotal());

            PedidoDto pedido = facade.createNew(entity);


            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/create_preference_mp")
    public PreferenceMP crearPreferenciaMercadoPago(@RequestBody PedidoCreateDto pedido){
        //Aca hay que calcular el total del pedido
        MercadoPagoController cMercadoPago = new MercadoPagoController();
        PreferenceMP preference = cMercadoPago.getPreferenciaIdMercadoPago(pedido);

        pedido.getFactura().setFormaPago(FormaPago.MERCADO_PAGO);
        pedido.getFactura().setFechaFcturacion(LocalDate.now());
        pedido.getFactura().setTotalVenta(pedido.getTotal());
        pedido.getFactura().setMpPreferenceId(preference.getId());
        //pedido.getFactura().setMpMerchantOrderId();
        //pedido.getFactura().setMpPaymentId(preference.getId());
        create(pedido);
        return preference;
    }
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.getById(id);
        if(pedido.getEstado() == Estado.PENDIENTE){
            pedido.setEstado(Estado.CANCELADO);
            pedidoRepository.save(pedido);
            return ResponseEntity.ok(facade.getById(id));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El pedido no está en estado pendiente y no puede ser cancelado.");
        }

    }
}
