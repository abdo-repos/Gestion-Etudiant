package com.ensa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Prof extends User{

    @OneToMany(mappedBy = "prof",targetEntity = Module.class)
    private List<Module> listModule;
}
