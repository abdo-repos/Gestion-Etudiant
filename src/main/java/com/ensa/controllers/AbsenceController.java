package com.ensa.controllers;

import com.ensa.entity.Absence;
import com.ensa.services.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api/absence/")
public class AbsenceController {

    private AbsenceService absenceService;

    @Autowired
    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @PostMapping
    public ResponseEntity<?> addAbsence(@RequestBody HashMap<String,Object> map){


        try {
            Absence absence = absenceService.addAbsence(map);
            return new ResponseEntity<>(absence,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list/{email}")
    public ResponseEntity<?> getAbsByStudent(@PathVariable String email){
        try {
            List<Absence> absences = absenceService.getAllAbsenceByStudent(email);
            return new ResponseEntity<>(absences,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
