package com.ensa.repositories;

import com.ensa.entity.Niveau;
import com.ensa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findAllByNiveau(Niveau niveau);
    Optional<Student> findByCin(String cin);
    Optional<Student> findByEmail(String email);
}
