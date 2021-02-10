package com.ensa.repositories;

import com.ensa.entity.Absence;
import com.ensa.entity.Module;
import com.ensa.entity.Note;
import com.ensa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

    Note findByModuleAndStudent(Module module, Student student);

    List<Note> findAllByStudent(Student student);

}
