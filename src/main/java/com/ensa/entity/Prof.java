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
public class Prof extends User{


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "prof",targetEntity = Module.class)
    private List<Module> listModule;
}
