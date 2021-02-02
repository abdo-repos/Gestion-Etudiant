package com.ensa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Absence implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private int nbAbsence;

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name = "id_Student",nullable = false)
    private Student student;

    @ManyToOne(targetEntity = Module.class)
    @JoinColumn(name = "id_module")
    private Module module;
}
