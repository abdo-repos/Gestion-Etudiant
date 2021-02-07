package com.ensa.controllers;

import com.ensa.entity.Filiere;
import com.ensa.entity.Niveau;
import com.ensa.services.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api/niveau/")
public class NiveauController {

    private final NiveauService niveauService;

    @Autowired
    public NiveauController(NiveauService niveauService) {
        this.niveauService = niveauService;
    }

    @PostMapping
    public ResponseEntity<?> addNiveau(@RequestBody  HashMap<String,Object> map){
        try {
            Niveau niveau= niveauService.addNiveau(map);
            return new ResponseEntity<>(niveau,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/list")
    public ResponseEntity<?> getAllNiveau(){
        List<Niveau> niveauList = niveauService.getAllNiveau();
        return new ResponseEntity<>(niveauList,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getNiveau(@PathVariable Long id){
        try {
            Niveau niveau = niveauService.getNiveau(id);
            return new ResponseEntity<>(niveau,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFiliere(@PathVariable Long id){
        try {
            Niveau niveau = niveauService.deleteNiveau(id);
            return new ResponseEntity<>(niveau, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
