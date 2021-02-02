package com.ensa.entity;

import com.ensa.enums.Roles;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
public class AllUser implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "cin is required")
    @Column(length = 10,nullable = false)
    private String cin;

    @Enumerated(EnumType.STRING)
    private Roles role;

    private Long idNiveau;
}
