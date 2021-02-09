package com.ensa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Semester implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "semester is required")
    @Column(name="libelle_semester",nullable = false,length = 20)
    private String libelle;


    @ManyToOne(targetEntity = Niveau.class)
    @JoinColumn(name = "id_niveau")
    private Niveau niveau;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "semester", targetEntity = Module.class)
    private List<Module> listModule;
}

