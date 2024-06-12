package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.impl.PedidoFacadeImpl;
import com.example.buensaborback.business.services.PedidoService;
import com.example.buensaborback.domain.dto.Pedido.PedidoCreateDto;
import com.example.buensaborback.domain.dto.Pedido.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.Estado;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.presentation.base.BaseControllerImpl;
import com.example.buensaborback.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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

    @PostMapping("/ingresos")
    public Optional<Double> getIngresos(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        LocalDate inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return pedidoRepository.getIngresos(inicio, fin);
    }

    @PostMapping("/cantidad-pedidos-por-cliente")
    public List<Object[]> getCantidadPedidosPorCliente(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        LocalDate inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return pedidoRepository.getCantidadPedidosPorCliente(inicio, fin);
    }

    @PostMapping("/ganancia")
    public Optional<Double> getGanancia(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        LocalDate inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return pedidoRepository.getGanancia(inicio, fin);
    }

    @PostMapping("/ranking-articulos")
    public List<Object[]> getRankingArticulos(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        LocalDate inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return pedidoRepository.getRankingArticulos(inicio, fin);
    }

    @PostMapping("/ranking-promociones")
    public List<Object[]> getRankingPromocion(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        LocalDate inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return pedidoRepository.getRankingPromocion(inicio, fin);
    }

    @PostMapping("/getPorFecha")
    public ResponseEntity<List<PedidoDto>> getPorFecha(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin){
        return ResponseEntity.ok(pedidoService.getPorFecha(fechaInicio,fechaFin));
    }

    @PostMapping("/getPorEstado/{estado}/{idCliente}")
    public ResponseEntity<List<PedidoDto>> getPorEstado(@PathVariable Long idCliente,@PathVariable String estado) {

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
    public PreferenceMP crearPreferenciaMercadoPago(@RequestBody PedidoCreateDto pedido) {
        //Aca hay que calcular el total del pedido
        MercadoPagoController cMercadoPago = new MercadoPagoController();
        PreferenceMP preference = cMercadoPago.getPreferenciaIdMercadoPago(pedido);
        return preference;
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
