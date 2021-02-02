package com.ensa.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Niveau implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "descNiveau is required")
    @Size(min = 3, message = "Niveau description must be greater than 3 char")
    @Column(name = "desc_niveau", nullable = false,length = 50)
    private String descNiveau;

    @ManyToOne(targetEntity = Filiere.class)
    @JoinColumn(name = "id_filiere", nullable = false)
    private Filiere filiere;

    @OneToMany(mappedBy = "niveau",targetEntity = Semester.class)
    private List<Semester> semesterList;

    @OneToMany(mappedBy = "niveau",targetEntity = Student.class)
    private List<Student> listStudent;
}
