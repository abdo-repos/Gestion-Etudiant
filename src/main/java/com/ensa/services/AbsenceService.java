package com.ensa.services;

import com.ensa.entity.Absence;
import com.ensa.entity.Module;
import com.ensa.entity.Student;
import com.ensa.repositories.AbsenceRepository;
import com.ensa.repositories.ModuleRepository;
import com.ensa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AbsenceService {

    private AbsenceRepository absenceRepository;
    private StudentRepository studentRepository;
    private ModuleRepository moduleRepository;

    @Autowired
    public AbsenceService(AbsenceRepository absenceRepository,StudentRepository studentRepository,
    ModuleRepository moduleRepository) {
        this.absenceRepository = absenceRepository;
        this.studentRepository = studentRepository;
        this.moduleRepository = moduleRepository;

    }

    public Absence addAbsence(HashMap<String,Object> map)throws Exception{


        Long idModule = Long.valueOf((Integer) map.get("module"));
        Module module = moduleRepository.findById(idModule).orElseThrow(()-> new Exception("the module with the given was not found"));

        Long idStudent = Long.valueOf((Integer) map.get("student"));
        Student student = studentRepository.findById(idStudent).orElseThrow(()-> new Exception("the student with the given id was not found"));

        Integer nbAbs = (Integer) map.get("nbAbs");

        Absence findAbsence = absenceRepository.findByModuleAndStudent(module,student);
        if(findAbsence !=null){
            findAbsence.setNbAbsence(nbAbs);
            return absenceRepository.save(findAbsence);
        }

        Absence absence = new Absence();
        absence.setNbAbsence(nbAbs);
        absence.setModule(module);
        absence.setStudent(student);
        return absenceRepository.save(absence);
    }

    public List<Absence> getAllAbsenceByStudent(String email) throws Exception{
        Student student = studentRepository.findByEmail(email).orElseThrow(()-> new Exception("the student with given id was not found "));

        List<Absence> absences = absenceRepository.findAllByStudent(student);
        return absences;
    }

    public List<Absence> getAllAbsenceByModule(Long id) throws Exception{
        Module module = moduleRepository.findById(id).orElseThrow(()-> new Exception("the module with given id was not found "));

        List<Absence> absences = absenceRepository.findAllByModule(module);

        return absences;
    }
}
