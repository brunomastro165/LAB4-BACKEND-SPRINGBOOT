package com.example.buensaborback.entities;

import com.example.buensaborback.entities.Base;
import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Usuario extends Base {

    private String auth0Id;
    private String username;

}
