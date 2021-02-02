package com.ensa.services;

import com.ensa.entity.Filiere;
import com.ensa.repositories.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliereService {
    private FiliereRepository filiereRepository;

    @Autowired
    public FiliereService(FiliereRepository filiereRepository){
        this.filiereRepository = filiereRepository;
    }

    public Filiere addFiliere(Filiere filiere) {

        Filiere savedFiliere = filiereRepository.save(filiere);
        return savedFiliere;
    }

    public Filiere updateFiliere(Long id,Filiere filiere) throws Exception{
                 Filiere f =  getFiliere(id);
                 f.setLibelle(filiere.getLibelle());
            return filiereRepository.save(f);
    }

    public Filiere getFiliere(Long id) throws Exception{
        if(id==null) throw new Exception("id is required");

        Filiere filiere = filiereRepository.findById(id)
                .orElseThrow(()-> new Exception("the filiere with the given id was not found "));

        return filiere;
    }

    public List<Filiere> getAllFilieres(){
            return filiereRepository.findAll();
    }

    public Filiere deleteFiliere(Long id) throws Exception{
        Filiere filiere = getFiliere(id);
        filiereRepository.deleteById(id);
        return  filiere;
    }
}
