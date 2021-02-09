package com.ensa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Module implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Module label is required ")
    @Column(name = "libelle_module",nullable = false,length = 40)
    private String libelle;


    @ManyToOne(targetEntity = Semester.class)
    @JoinColumn(name = "id_semester")
    private Semester semester;


    @ManyToOne(targetEntity = Prof.class)
    @JoinColumn(name = "id_prof")
    private Prof prof;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "module", targetEntity = Cours.class)
    private List<Cours> listCours;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "module", targetEntity = Note.class)
    private List<Note> listNote;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "module", targetEntity = Absence.class)
    private List<Absence> listAbsence;
}
