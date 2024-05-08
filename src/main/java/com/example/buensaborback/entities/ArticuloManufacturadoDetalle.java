package com.example.buensaborback.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
//@Audited
public class ArticuloManufacturadoDetalle extends Base{
    private Integer cantidad;

    @ManyToOne
    private ArticuloInsumo articuloInsumo;
}