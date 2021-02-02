package com.ensa.services;

import com.ensa.entity.Filiere;
import com.ensa.entity.Niveau;
import com.ensa.repositories.FiliereRepository;
import com.ensa.repositories.NiveauRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class NiveauService {

    private final NiveauRepository niveauRepository;
    private final FiliereRepository filiereRepository;

    @Autowired
    public NiveauService(NiveauRepository niveauRepository,FiliereRepository filiereRepository) {
        this.niveauRepository = niveauRepository;
        this.filiereRepository = filiereRepository;
    }


    public Niveau addNiveau(HashMap<String,Object> map) throws Exception{
        Long idF = (Long) map.get("filiere");
        map.remove("filiere");

        Filiere filiere = filiereRepository.findById(idF).orElseThrow(()-> new Exception("Filiere not found"));

        map.put("filiere",filiere);

        ObjectMapper objectMapper = new ObjectMapper();
        Niveau niveau = objectMapper.convertValue(map,Niveau.class);

        return niveauRepository.save(niveau);
    }
}
