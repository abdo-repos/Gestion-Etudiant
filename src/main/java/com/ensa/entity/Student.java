package com.ensa.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User {


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student",targetEntity = Note.class)
    private List<Note> listNote;


    @ManyToOne(targetEntity = Niveau.class)
    @JoinColumn(name="id_niveau", nullable = false)
    private Niveau niveau;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student",targetEntity = Absence.class)
    List<Absence> listAbsence;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student",targetEntity = DemandeService.class)
    List<DemandeService> listDemandeService;
}
