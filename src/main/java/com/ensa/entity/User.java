package com.ensa.entity;

import com.ensa.enums.Roles;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "cin is required")
    @Column(length = 10, nullable = false,unique = true)
    private String cin;

    @NotBlank(message = "FirstName is required")
    @Column(length = 20,nullable = false)
    private String firstName;


    @NotBlank(message = "LastName is required")
    @Column(length = 20,nullable = false)
    private String lastName;

    @NotBlank( message = "Email is required")
    @Email(message = "Invalid email")
    @Column(length=50,nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;
}
