package com.ensa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Data
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {

}

