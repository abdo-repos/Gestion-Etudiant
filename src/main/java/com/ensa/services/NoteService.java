package com.ensa.services;

import com.ensa.entity.Absence;
import com.ensa.entity.Module;
import com.ensa.entity.Note;
import com.ensa.entity.Student;
import com.ensa.repositories.ModuleRepository;
import com.ensa.repositories.NoteRepository;
import com.ensa.repositories.StudentRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class NoteService {
    private NoteRepository noteRepository;
    private StudentRepository studentRepository;
    private ModuleRepository moduleRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository,StudentRepository studentRepository,
                          ModuleRepository moduleRepository) {
        this.noteRepository = noteRepository;
        this.studentRepository = studentRepository;
        this.moduleRepository = moduleRepository;
    }

    public Note addNote(HashMap<String,Object> map)throws Exception{
        Long idModule = Long.valueOf((Integer) map.get("module"));
        Module module = moduleRepository.findById(idModule).orElseThrow(()-> new Exception("the module with the given was not found"));

        Long idStudent = Long.valueOf((Integer) map.get("student"));
        Student student = studentRepository.findById(idStudent).orElseThrow(()-> new Exception("the student with the given id was not found"));

        Integer note = (Integer) map.get("note");

        Note findNote = noteRepository.findByModuleAndStudent(module,student);
        if (findNote!=null){
            findNote.setNote(note);
            return noteRepository.save(findNote);
        }

        Note savedNote = new Note();
        savedNote.setNote(note);
        savedNote.setModule(module);
        savedNote.setStudent(student);
        return noteRepository.save(savedNote);
    }

    public List<Note> getAllNoteByStudent(String email) throws Exception{
        Student student = studentRepository.findByEmail(email).orElseThrow(()-> new Exception("the student with given id was not found "));
        List<Note> notes = noteRepository.findAllByStudent(student);
        return notes;
    }

    public List<Note> getAllAbsenceByModule(Long id) throws Exception{
        Module module = moduleRepository.findById(id).orElseThrow(()-> new Exception("the module with given id was not found "));

        List<Note> notes = noteRepository.findAllByModule(module);
        return notes;
    }
}
