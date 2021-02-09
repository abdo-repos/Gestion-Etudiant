package com.ensa.services;

import com.ensa.entity.Niveau;
import com.ensa.entity.Student;
import com.ensa.repositories.NiveauRepository;
import com.ensa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private NiveauRepository niveauRepository;


    @Autowired
    public StudentService(StudentRepository studentRepository,NiveauRepository niveauRepository) {
        this.studentRepository = studentRepository;
        this.niveauRepository = niveauRepository;
    }

    public List<Student> getStudentByNiveau(Long id)throws Exception{

        Niveau niveau = niveauRepository.findById(id).orElseThrow(()->new Exception("the niveau with the given was not found "));

        return studentRepository.findAllByNiveau(niveau);
    }
}
