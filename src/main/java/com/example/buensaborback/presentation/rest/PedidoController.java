package com.example.buensaborback.presentation.rest;

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
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import com.example.buensaborback.repositories.ArticuloRepository;
import com.example.buensaborback.repositories.DetallePedidoRepository;
import com.example.buensaborback.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/pedido")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoDto, PedidoCreateDto, PedidoCreateDto, Long, PedidoFacadeImpl> {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    @Autowired
    private ArticuloRepository articuloRepository;
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
        /*

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

         */

        return ResponseEntity.ok(pedidos);
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> getById(@PathVariable Long id) {
        //obtengo el pedido con el facade aca los articulos son nulos
        PedidoDto pedido = facade.getById(id);
        /*
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
        */
        return ResponseEntity.ok(pedido);
    }


    @PostMapping()
    public ResponseEntity<PedidoDto> create(@RequestBody PedidoCreateDto entity) {
        try {
            PedidoDto pedido = facade.createNew(entity);
            /*
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

             */
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
        create(pedido);
        return preference;
    }
}
