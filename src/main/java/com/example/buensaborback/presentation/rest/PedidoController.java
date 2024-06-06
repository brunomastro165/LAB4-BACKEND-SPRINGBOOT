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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/pedido")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoDto, PedidoCreateDto, PedidoCreateDto, Long, PedidoFacadeImpl> {
    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;
    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ArticuloManufacturadoMapper articuloManufacturadoMapper;
    @Autowired
    private ArticuloInsumoMapper articuloInsumoMapper;

    public PedidoController(PedidoFacadeImpl facade) {
        super(facade);
    }
    @GetMapping("")
    public ResponseEntity<List<PedidoDto>> getAll() {
        // Obtén todos los pedidos con el facade
        List<PedidoDto> pedidos = facade.getAll();


        for (PedidoDto pedido : pedidos) {
            if (!pedido.isEliminado() && pedidoRepository.getById(pedido.getId()).getDetallesPedido() != null) {
                // Crea un array de detalles
                Set<DetallePedido> detalles = pedidoRepository.getById(pedido.getId()).getDetallesPedido();

                Set<DetallePedidoDto> newDetalles = new HashSet<>();
                for (DetallePedido detalle : detalles) {
                    // Comprueba si el detalle ha sido eliminado
                    if (!detalle.isEliminado() && detalle.getArticulo() != null) {
                        Articulo articulo = articuloRepository.getById(detalle.getArticulo().getId());
                        System.out.println(articulo);
                        if (articulo instanceof ArticuloManufacturado) {
                            System.out.println("El detalle:" + detalle.getId() + " tiene un articuloManufacturado");
                            ArticuloManufacturadoDto dto = articuloManufacturadoMapper.toDTO((ArticuloManufacturado) articulo);
                            newDetalles.add(new DetallePedidoDto(detalle.getCantidad(),detalle.getSubTotal(), null, dto));
                        } else if (articulo instanceof ArticuloInsumo) {
                            System.out.println("El detalle:" + detalle.getId() + " tiene un articuloInsumo");
                            ArticuloInsumoDto insumoDto = articuloInsumoMapper.toDTO((ArticuloInsumo) articulo);
                            newDetalles.add(new DetallePedidoDto(detalle.getCantidad(),detalle.getSubTotal(), insumoDto, null));
                        } else {
                            System.out.println("El detalle:" + detalle.getId() + " no tiene articulo asignado");
                        }
                    }
                }
                pedido.setDetallesPedido(newDetalles);
            }
        }



        return ResponseEntity.ok(pedidos);
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> getById(@PathVariable Long id) {
        //obtengo el pedido con el facade aca los articulos son nulos
        PedidoDto pedido = facade.getById(id);

        //creo un array de detalles posta
        Set<DetallePedido> detalles = pedidoRepository.getById(pedido.getId()).getDetallesPedido();

        Set<DetallePedidoDto> newDetalles = new HashSet<>();
        for (DetallePedido detalle : detalles) {
            Articulo articulo = articuloRepository.getById(detalle.getArticulo().getId());
            System.out.println(articulo);
            if (articulo instanceof ArticuloManufacturado) {
                System.out.println("El detalle:" + detalle.getId() + " tiene un articuloManufacturado");
                ArticuloManufacturadoDto dto = articuloManufacturadoMapper.toDTO((ArticuloManufacturado) articulo);
                newDetalles.add(new DetallePedidoDto(detalle.getCantidad(),detalle.getSubTotal(), null, dto));
            } else if (articulo instanceof ArticuloInsumo) {
                System.out.println("El detalle:" + detalle.getId() + " tiene un articuloInsumo");
                ArticuloInsumoDto insumoDto = articuloInsumoMapper.toDTO((ArticuloInsumo) articulo);
                newDetalles.add(new DetallePedidoDto(detalle.getCantidad(),detalle.getSubTotal(), insumoDto, null));
            } else {
                System.out.println("El detalle:" + detalle.getId() + " no tiene articulo asignado");
            }
        }
        pedido.setDetallesPedido(newDetalles);

        return ResponseEntity.ok(pedido);
    }
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
    @PostMapping()
    public ResponseEntity<PedidoDto> create(@RequestBody PedidoCreateDto entity) {
        try {
            LocalTime horaFinalizado = LocalTime.now();
            boolean denegar = false;
            // Calcular el costo total que le cuesta a la empresa
            double costoTotal = 0;
            // Calcular el precio total que paga el cliente
            double total = 0;
            for (DetallePedidoCreateDto detalle:
                    entity.getDetallesPedido()) {
                Articulo articulo = articuloRepository.getById(detalle.getIdArticulo());
                detalle.setSubTotal(articulo.getPrecioVenta());
                total += detalle.getSubTotal();
                if(articulo instanceof ArticuloInsumo){
                    ArticuloInsumo articuloInsumo = articuloInsumoRepository.getById(detalle.getIdArticulo());
                    costoTotal += articuloInsumo.getPrecioCompra();
                    if(articuloInsumo.getStockActual() <= articuloInsumo.getStockMinimo()){
                        denegar = true;
                    }

                }
                if(articulo instanceof ArticuloManufacturado){
                    ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.getById(detalle.getIdArticulo());
                    horaFinalizado = horaFinalizado.plusMinutes(articuloManufacturado.getTiempoEstimadoMinutos());
                    for (ArticuloManufacturadoDetalle detalle1:
                            articuloManufacturado.getArticuloManufacturadoDetalles()) {
                        if(detalle1.getArticuloInsumo().getStockActual() <= detalle1.getArticuloInsumo().getStockMinimo())
                            denegar = true;
                        costoTotal += detalle1.getArticuloInsumo().getPrecioVenta();
                    }
                }
            }
            if(!denegar){
                for (DetallePedidoCreateDto detalle:
                        entity.getDetallesPedido()) {
                    Articulo articulo = articuloRepository.getById(detalle.getIdArticulo());
                    if(articulo instanceof ArticuloInsumo){
                        ArticuloInsumo articuloInsumo = articuloInsumoRepository.getById(detalle.getIdArticulo());
                        if(articuloInsumo.getStockActual()-detalle.getCantidad() <= articuloInsumo.getStockMinimo()){
                            denegar = true;
                            break;
                        }
                        articuloInsumo.setStockActual(articuloInsumo.getStockActual()-detalle.getCantidad());
                        articuloInsumoRepository.save(articuloInsumo);
                    }
                    if(articulo instanceof ArticuloManufacturado){
                        ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.getById(detalle.getIdArticulo());
                        for (ArticuloManufacturadoDetalle detalle1:
                                articuloManufacturado.getArticuloManufacturadoDetalles()) {

                            ArticuloInsumo articuloInsumo = articuloInsumoRepository.getById(detalle1.getArticuloInsumo().getId());
                            if(articuloInsumo.getStockActual()-detalle1.getCantidad() * detalle.getCantidad() <= articuloInsumo.getStockMinimo()){
                                denegar = true;
                                break;
                            }
                            articuloInsumo.setStockActual(articuloInsumo.getStockActual()-detalle1.getCantidad() * detalle.getCantidad());
                            articuloInsumoRepository.save(articuloInsumo);

                        }
                        if(denegar)
                            break;
                    }
                }
            }
            entity.setHoraEstimadaFinalizacion(horaFinalizado);
            entity.setTotal(total);
            entity.setTotalCosto(costoTotal);
            if(denegar)
                entity.setEstado(Estado.RECHAZADO);
            else
                entity.setEstado(Estado.PENDIENTE);

            entity.setFechaPedido(LocalDate.now());
            entity.getFactura().setFormaPago(FormaPago.EFECTIVO);
            entity.getFactura().setFechaFcturacion(LocalDate.now());
            entity.getFactura().setTotalVenta(entity.getTotal());
            PedidoDto pedido = facade.createNew(entity);
            Pedido p = pedidoRepository.getById(pedido.getId());
            p.setCliente(clienteRepository.getById(entity.getIdCliente()));
            pedidoRepository.save(p);

            Set<DetallePedido> detalles = pedidoRepository.getById(pedido.getId()).getDetallesPedido();
            List<DetallePedido> detalles2 = new ArrayList<>();
            for (DetallePedido detalle :
                    detalles) {
                detalles2.add(detalle);
            }
            int i = 0;
            for (DetallePedidoCreateDto detalle :
                    entity.getDetallesPedido()) {
                DetallePedido aux = detallePedidoRepository.getById(detalles2.get(i).getId());
                System.out.println(articuloRepository.getById(detalle.getIdArticulo()));
                aux.setArticulo(articuloRepository.getById(detalle.getIdArticulo()));
                detallePedidoRepository.save(aux);
                i++;
            }


            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/create_preference_mp")
    public PreferenceMP crearPreferenciaMercadoPago(@RequestBody PedidoCreateDto pedido){
        //Aca hay que calcular el total del pedido
        MercadoPagoController cMercadoPago = new MercadoPagoController();
        PreferenceMP preference = cMercadoPago.getPreferenciaIdMercadoPago(pedido);
        LocalTime horaFinalizado = LocalTime.now();
        boolean denegar = false;
        // Calcular el costo total que le cuesta a la empresa
        double costoTotal = 0;
        // Calcular el precio total que paga el cliente
        double total = 0;
        for (DetallePedidoCreateDto detalle:
                pedido.getDetallesPedido()) {
            Articulo articulo = articuloRepository.getById(detalle.getIdArticulo());
            detalle.setSubTotal(articulo.getPrecioVenta());
            total += detalle.getSubTotal();
            if(articulo instanceof ArticuloInsumo){
                ArticuloInsumo articuloInsumo = articuloInsumoRepository.getById(detalle.getIdArticulo());
                costoTotal += articuloInsumo.getPrecioCompra();
                if(articuloInsumo.getStockActual() <= articuloInsumo.getStockMinimo()){
                    denegar = true;
                }
            }
            if(articulo instanceof ArticuloManufacturado){
                ArticuloManufacturado articuloManufacturado = articuloManufacturadoRepository.getById(detalle.getIdArticulo());
                horaFinalizado = horaFinalizado.plusMinutes(articuloManufacturado.getTiempoEstimadoMinutos());
                for (ArticuloManufacturadoDetalle detalle1:
                        articuloManufacturado.getArticuloManufacturadoDetalles()) {
                    if(detalle1.getArticuloInsumo().getStockActual() <= detalle1.getArticuloInsumo().getStockMinimo())
                        denegar = true;
                    costoTotal += detalle1.getArticuloInsumo().getPrecioVenta();
                }
            }
        }
        pedido.setHoraEstimadaFinalizacion(horaFinalizado);
        pedido.setTotal(total);
        pedido.setTotalCosto(costoTotal);
        if(denegar)
            pedido.setEstado(Estado.RECHAZADO);
        else
            pedido.setEstado(Estado.PENDIENTE);
        pedido.getFactura().setFormaPago(FormaPago.MERCADO_PAGO);
        pedido.setFechaPedido(LocalDate.now());
        pedido.getFactura().setFechaFcturacion(LocalDate.now());
        pedido.getFactura().setTotalVenta(pedido.getTotal());
        pedido.getFactura().setMpPreferenceId(preference.getId());
        //pedido.getFactura().setMpMerchantOrderId();
        //pedido.getFactura().setMpPaymentId(preference.getId());
        create(pedido);
        return preference;
    }
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
