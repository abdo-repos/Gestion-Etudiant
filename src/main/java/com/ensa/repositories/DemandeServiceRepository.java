package com.ensa.repositories;

import com.ensa.entity.DemandeService;
import com.ensa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeServiceRepository extends JpaRepository<DemandeService,Long> {

    List<DemandeService> findAllByStudent(Student student);
}
