package com.ensa.repositories;

import com.ensa.entity.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence,Long> {

    @Query("from Absence where module.id = (:idModule)")
    List<Absence> findAbsenceByIdModule(@Param("idModule") Long idModule);

    @Query("select sum(nbAbsence) from  Absence group by module.id having student.id = (:idStudent) AND module.id = (:idModule)")
    int nbAbsByModByStud(@Param("idModule") Long idModule, @Param("idStudent") Long idStudent);

}
