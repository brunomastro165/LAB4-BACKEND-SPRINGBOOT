package com.example.buensaborback.domain.entities;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Audited
public class UsuarioEmpleado extends Base {
    private String auth0Id;
    private String userName;
}
