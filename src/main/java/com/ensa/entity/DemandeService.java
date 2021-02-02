package com.ensa.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
public class DemandeService implements Serializable {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "demande of service cannot be empty")
    @Column(nullable = false)
    private String content;

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name = "id_student",nullable = false)
    private Student student;
}
