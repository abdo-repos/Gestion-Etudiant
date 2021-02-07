package com.ensa.controllers;

import com.ensa.entity.Semester;
import com.ensa.services.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/api/semester/")
public class SemesterController {

    private SemesterService semesterService;

    @Autowired
    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @PostMapping
    public ResponseEntity<?> addSemester(@RequestBody HashMap<String,Object> map){
        try {
            Semester semester = semesterService.addSemester(map);
            return new ResponseEntity<>(semester, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/list")
    public ResponseEntity<?> getAllSemester(){
        return new ResponseEntity<>(semesterService.getAllSemester(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSemester(@PathVariable Long id){
        try {
            Semester semester = semesterService.getSemester(id);
            return new ResponseEntity<>(semester,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSemester(@PathVariable Long id) {
        try {

            Semester semester = semesterService.deleteSemster(id);
            return new ResponseEntity<>(semester,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
