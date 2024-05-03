package com.example.buensaborback.entities;

import com.example.buensaborback.entities.enums.Estado;
import com.example.buensaborback.entities.enums.FormaPago;
import com.example.buensaborback.entities.enums.TipoEnvio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@JsonIgnoreProperties({"domicilio","sucursal","factura","cliente",})
public class Pedido extends Base {

    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    private FormaPago formaPago;
    private LocalDate fechaPedido;

    @ManyToOne
    private Domicilio domicilio;

    @ManyToOne
    private Sucursal sucursal;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "pedido")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "pedido")
    //SE AGREGA EL BUILDER.DEFAULT PARA QUE BUILDER NO SOBREESCRIBA LA INICIALIZACION DE LA LISTA
    @Builder.Default
    private Set<DetallePedido> detallePedidos = new HashSet<>();
}
