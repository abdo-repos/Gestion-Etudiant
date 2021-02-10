package com.ensa.controllers;

import com.ensa.entity.DemandeService;

import com.ensa.services.ServDemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api/service/")
public class ServDemController {

    private ServDemService servDemService;

    @Autowired
    public ServDemController(ServDemService servDemService) {
        this.servDemService = servDemService;
    }

    @PostMapping
    public ResponseEntity<?> addDemande(@RequestBody HashMap<String,String> map){
        System.out.println(map);
        try{
            DemandeService d = servDemService.addDemande(map);
            return new ResponseEntity<>(d,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllDemande(){
        return new ResponseEntity<>(servDemService.getAllDemande(),HttpStatus.OK);
    }

    @GetMapping("/list/{email}")
    public ResponseEntity<?> getDemandeByStudent(@PathVariable String email){
        try{
            List<DemandeService> serviceList = servDemService.getDemandeServiceByStudent(email);
            return new ResponseEntity<>(serviceList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateDemande(@PathVariable Long id, @RequestBody HashMap<String,String> map){

        try {
            DemandeService d = servDemService.updateDemande(id,map);
            return new ResponseEntity<>(d, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
