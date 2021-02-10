package com.ensa.controllers;

import com.ensa.entity.Note;
import com.ensa.repositories.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api/note/")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<?> addNote(@RequestBody HashMap<String,Object> map){
        try {
            Note n = noteService.addNote(map);
            return new ResponseEntity<>(n,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list/{email}")
    public ResponseEntity<?> getNoteByStudent(@PathVariable String email){
        try {
            List<Note> notes = noteService.getAllNoteByStudent(email);
            return new ResponseEntity<>(notes,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
