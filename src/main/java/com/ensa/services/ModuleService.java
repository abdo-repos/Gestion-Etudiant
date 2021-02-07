package com.ensa.services;

import com.ensa.entity.Module;
import com.ensa.entity.Prof;
import com.ensa.entity.Semester;
import com.ensa.repositories.ModuleRepository;
import com.ensa.repositories.ProfRepository;
import com.ensa.repositories.SemesterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ModuleService {

    private ModuleRepository moduleRepository;
    private ProfRepository profRepository;
    private SemesterRepository semesterRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository, ProfRepository profRepository, SemesterRepository semesterRepository) {
        this.moduleRepository = moduleRepository;
        this.profRepository = profRepository;
        this.semesterRepository = semesterRepository;
    }

    public Module addModule(HashMap<String,Object> map) throws Exception{
        Long idS = Long.valueOf((String) map.get("semester"));
        map.remove("semester");

        Long idP = Long.valueOf((String) map.get("prof"));
        map.remove("prof");

        Semester semester = semesterRepository.findById(idS).orElseThrow(()->new Exception("semester not found "));
        Prof prof = profRepository.findById(idP).orElseThrow(()->new Exception("Professor not found "));

        map.put("semester",semester);
        map.put("prof",prof);

        ObjectMapper objectMapper = new ObjectMapper();
        Module module = objectMapper.convertValue(map,Module.class);
        return moduleRepository.save(module);
    }

    public List<Module> getAllModule(){return moduleRepository.findAll();}

    public Module getModule(Long id)throws Exception{
        if(id==null) throw new Exception("id is required");
        Module module = moduleRepository.findById(id)
                .orElseThrow(()-> new Exception("the module with the given id was not found "));

        return module;
    }

    public Module deleteModule(Long id)throws Exception{
        Module module = getModule(id);
        moduleRepository.deleteById(id);
        return module;
    }
}
