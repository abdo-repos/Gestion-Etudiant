package com.ensa.repositories;

import com.ensa.entity.Absence;
import com.ensa.entity.Module;
import com.ensa.entity.Student;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence,Long> {

    @Query("from Absence where module.id = (:idModule)")
    List<Absence> findAbsenceByIdModule(@Param("idModule") Long idModule);

    Absence findByModuleAndStudent(Module module, Student student);

    @Query("select sum(nbAbsence) from  Absence group by module.id having student.id = (:idStudent) AND module.id = (:idModule)")
    int nbAbsByModByStud(@Param("idModule") Long idModule, @Param("idStudent") Long idStudent);

    List<Absence> findAllByStudent(Student student);

    List<Absence> findAllByModule(Module module);
}
