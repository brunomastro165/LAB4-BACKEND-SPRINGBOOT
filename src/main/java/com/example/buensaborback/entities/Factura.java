package com.example.buensaborback.entities;

import com.example.buensaborback.entities.Base;
import com.example.buensaborback.entities.enums.FormaPago;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
@JsonIgnoreProperties({"pedidoId"})
public class Factura extends Base {

    private LocalDate fechaFacturacion;
    private Integer mpPaymentId;
    private Integer mpMerchantOrderId;
    private String mpPreferenceId;
    private String mpPaymentType;
    private FormaPago formaPago;
    private Double totalVenta;
    @OneToOne
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;

}
