package com.ensa.controllers;

import com.ensa.entity.Filiere;
import com.ensa.services.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/filiere/")
public class FiliereController {

    private FiliereService filiereService;

    @Autowired
    public FiliereController(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

    @PostMapping
    public ResponseEntity<?> addFiliere(@Valid @RequestBody Filiere filiere){

             Filiere f = filiereService.addFiliere(filiere);
            return new ResponseEntity<>(f, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getFiliere(@PathVariable Long id){
       try {
           Filiere filiere = filiereService.getFiliere(id);
           return new ResponseEntity<>(filiere, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFiliere(@PathVariable Long id){
        try {
            Filiere filiere = filiereService.deleteFiliere(id);
            return new ResponseEntity<>(filiere, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateFiliere(@PathVariable Long id, @Valid @RequestBody Filiere filiere){
        try {
            Filiere f = filiereService.updateFiliere(id,filiere);
            return new ResponseEntity<>(f, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "/list")
    public ResponseEntity<?> getAllFilliers(){
        List<Filiere> filiereList = filiereService.getAllFilieres();

        return new ResponseEntity<>(filiereList,HttpStatus.OK);
    }
}
