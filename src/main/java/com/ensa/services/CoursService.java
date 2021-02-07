package com.ensa.services;

import com.ensa.entity.Cours;
import com.ensa.entity.Module;
import com.ensa.repositories.CoursRepository;
import com.ensa.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Service
public class CoursService {

    private CoursRepository coursRepository;
    private ModuleRepository moduleRepository;

    @Autowired
    public CoursService(CoursRepository coursRepository, ModuleRepository moduleRepository) {
        this.coursRepository = coursRepository;

        this.moduleRepository = moduleRepository;
    }


    public Cours addCours(String libelle, String module, String url) throws Exception{

        if (libelle == null) throw new Exception("label of cours is required");

        Long idM = Long.valueOf(module);
       Module module1=  moduleRepository.findById(idM).orElseThrow(()-> new Exception("the module with the given id was not found "));

        Cours c = new Cours();
        c.setContent(url);
        c.setLibelle(libelle);
        c.setModule(module1);

        return coursRepository.save(c);
    }

    public Cours getCours(Long id) throws Exception{
        if(id ==null) throw new Exception("id is required");

        Cours c = coursRepository.findById(id)
                .orElseThrow(()->new Exception("the Cours with the given id was not found"));

        return c;


    }

    public List<Cours> getAllCours(){return coursRepository.findAll();}

    public Cours deleteCours(Long id)throws Exception{
        Cours c=  getCours(id);

        coursRepository.deleteById(id);

        return c;
    }

}
