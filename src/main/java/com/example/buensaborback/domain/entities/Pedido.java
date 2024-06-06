package com.example.buensaborback.domain.entities;


import com.example.buensaborback.domain.enums.Estado;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.domain.enums.TipoEnvio;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
//@Audited
public class Pedido extends Base {

    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    private FormaPago formaPago;
    private LocalDate fechaPedido;

    @ManyToOne(cascade = CascadeType.ALL)
    private Domicilio domicilio;

    @ManyToOne
    private Sucursal sucursal;

    @OneToOne(cascade = CascadeType.ALL)
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private Set<DetallePedido> detallesPedido = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
}
