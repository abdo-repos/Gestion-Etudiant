package com.ensa.services;

import com.ensa.entity.Niveau;
import com.ensa.entity.Semester;
import com.ensa.repositories.NiveauRepository;
import com.ensa.repositories.SemesterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SemesterService {

    private SemesterRepository semesterRepository;
    private NiveauRepository niveauRepository;

    @Autowired
    public SemesterService(SemesterRepository semesterRepository, NiveauRepository niveauRepository) {
        this.semesterRepository = semesterRepository;
        this.niveauRepository = niveauRepository;
    }

    public Semester addSemester(HashMap<String,Object> map) throws Exception{
        Long idN = Long.valueOf((String) map.get("niveau"));
        map.remove("niveau");

        Niveau niveau = niveauRepository.findById(idN).orElseThrow(()->new Exception("niveau not found "));
        map.put("niveau",niveau);

        ObjectMapper objectMapper = new ObjectMapper();
        Semester semester = objectMapper.convertValue(map,Semester.class);

        return semesterRepository.save(semester);
    }

    public List<Semester> getAllSemester(){
        return semesterRepository.findAll();
    }

    public Semester getSemester(Long id)throws Exception{
        if(id==null) throw new Exception("id is required");
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(()-> new Exception("the semester with the given id was not found "));

        return semester;
    }

    public Semester deleteSemster(Long id) throws Exception{
        Semester semester = getSemester(id);
        semesterRepository.deleteById(id);
        return  semester;
    }

}
