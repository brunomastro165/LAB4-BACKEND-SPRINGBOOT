package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.PedidoFacadeImpl;
import com.example.buensaborback.business.services.PedidoService;
import com.example.buensaborback.domain.dto.Pedido.PedidoCreateDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.Estado;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import com.example.buensaborback.repositories.PedidoRepository;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/pedido")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoDto, PedidoCreateDto, PedidoCreateDto, Long, PedidoFacadeImpl> {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoService pedidoService;

    public PedidoController(PedidoFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("/ingresos/{idSucursal}")
    public Optional<Double> getIngresos(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin, @PathVariable Long idSucursal) {
        LocalDate inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return pedidoRepository.getIngresos(inicio, fin, idSucursal);
    }

    @GetMapping("/cantidad-pedidos-por-cliente/{idSucursal}")
    public List<Object[]> getCantidadPedidosPorCliente(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin, @PathVariable Long idSucursal) {
        LocalDate inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return pedidoRepository.getCantidadPedidosPorCliente(inicio, fin, idSucursal);
    }

    @GetMapping("/ganancia/{idSucursal}")
    public Optional<Double> getGanancia(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin, @PathVariable Long idSucursal) {
        LocalDate inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return pedidoRepository.getGanancia(inicio, fin, idSucursal);
    }

    @GetMapping("/ranking-articulos/{idSucursal}")
    public List<Object[]> getRankingArticulos(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin, @PathVariable Long idSucursal) {
        LocalDate inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return pedidoRepository.getRankingArticulos(inicio, fin, idSucursal);
    }

    @GetMapping("/ranking-promociones/{idSucursal}")
    public List<Object[]> getRankingPromocion(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin, @PathVariable Long idSucursal) {
        LocalDate inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return pedidoRepository.getRankingPromocion(inicio, fin, idSucursal);
    }

    @GetMapping("/getPorFecha/{idSucursal}")
    public ResponseEntity<List<PedidoDto>> getPorFecha(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin, @PathVariable Long idSucursal) {
        return ResponseEntity.ok(pedidoService.getPorFecha(fechaInicio, fechaFin, idSucursal));
    }

    @PostMapping("/getPorEstado/{estado}/{idCliente}")
    public ResponseEntity<List<PedidoDto>> getPorEstado(@PathVariable Long idCliente, @PathVariable String estado) {

        // Obtén todos los pedidos con el facade
        List<PedidoDto> pedidos = facade.getAll();
        List<PedidoDto> filteredPedidos = pedidos.stream()
                .filter(a -> a.getCliente() != null
                        && a.getCliente().getId().equals(idCliente)
                        && !a.isEliminado()
                        && a.getEstado().toString().equals(estado))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredPedidos);
    }

    @GetMapping("/getPorFechaYClienteYEstado/{idSucursal}/{idCliente}")
    public ResponseEntity<List<PedidoDto>> getPorFechaYClienteYEstado(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin,
            @PathVariable Long idSucursal,
            @PathVariable Long idCliente,
            @RequestParam String estado) {
        List<PedidoDto> pedidos = pedidoService.getPorFecha(fechaInicio, fechaFin, idSucursal);
        List<PedidoDto> filteredPedidos = pedidos.stream()
                .filter(p -> p.getCliente() != null
                        && p.getCliente().getId().equals(idCliente)
                        && !p.isEliminado()
                        && p.getEstado().toString().equals(estado))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredPedidos);
    }

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


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> getById(@PathVariable Long id) {
        //obtengo el pedido con el facade aca los articulos son nulos
        PedidoDto pedido = facade.getById(id);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/asignarEmpleado/{idEmpleado}/{idPedido}")
    public ResponseEntity<?> asignarEmpleado(@PathVariable Long idEmpleado, @PathVariable Long idPedido) {
        return ResponseEntity.ok(facade.asignarEmpleado(idEmpleado, idPedido));
    }

    @PutMapping("/cambiarEstado/{id}")
    public ResponseEntity<?> cambiarEstado(@RequestBody Estado entity, @PathVariable Long id) {
        try {
            Pedido pedido = pedidoRepository.getById(id);
            pedido.setEstado(entity);
            pedidoRepository.save(pedido);
            return ResponseEntity.ok(facade.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El pedido no ha podido cambiarse");
        }

    }


    @PostMapping()
    public ResponseEntity<PedidoDto> create(@RequestBody PedidoCreateDto entity) {
        try {
            return ResponseEntity.ok(facade.createNew(entity));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/create_preference_mp")
    public ResponseEntity<PedidoDto> crearPreferenciaMercadoPago(@RequestBody PedidoCreateDto pedido) {
        MercadoPagoController cMercadoPago = new MercadoPagoController();
        Preference preference = cMercadoPago.getPreferenciaIdMercadoPago(pedido);
        return create(pedido);
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.getById(id);
        if (pedido.getEstado() == Estado.PENDIENTE) {
            pedido.setEstado(Estado.CANCELADO);
            pedidoRepository.save(pedido);
            return ResponseEntity.ok(facade.getById(id));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El pedido no está en estado pendiente y no puede ser cancelado.");
        }

    }
}
