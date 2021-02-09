package com.ensa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
public class Cours implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle_cours",nullable = false)
    @NotNull(message = "cours label is required")
    private String libelle;

    @Column(name = "Content_cours",nullable = false)
    @NotNull(message = "content is required")
    private String content;

    @ManyToOne(targetEntity = Module.class)
    @JoinColumn(name = "id_module")
    private Module module;

}
