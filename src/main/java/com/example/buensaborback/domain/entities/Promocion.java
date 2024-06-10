package com.example.buensaborback.domain.entities;

import com.example.buensaborback.domain.enums.TipoPromocion;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.NotAudited;

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
public class Promocion extends Base {
    private String denominacion;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    @Schema(type = "string", format = "time", pattern = "HH:mm:ss", description = "Formato del horario: HH:mm:ss")
    private LocalTime horaDesde;
    @Schema(type = "string", format = "time", pattern = "HH:mm:ss", description = "Formato del horario: HH:mm:ss")
    private LocalTime horaHasta;
    private String descripcionDescuento;
    private Double precioPromocional;
    private TipoPromocion tipoPromocion;
    @OneToMany
    @JoinColumn(name = "promocion_id")
    @Builder.Default
    @NotAudited
    private Set<ImagenPromocion> imagenes = new HashSet<>();
    @ManyToMany(mappedBy = "promociones")
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "promocion_id")
    @Builder.Default
    private Set<PromocionDetalle> detalles = new HashSet<>();

}
