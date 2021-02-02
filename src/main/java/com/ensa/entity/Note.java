package com.ensa.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "mark is required")
    @Min(value = 0, message = "mark shouldn't be lower than 0")
    private double note;

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name = "id_student",nullable = false)
    private Student student;

    @ManyToOne(targetEntity = Module.class)
    @JoinColumn(name="id_module",nullable = false)
    private Module module;
}
