package com.ensa.controllers;

import com.ensa.entity.Student;
import com.ensa.entity.User;
import com.ensa.helpers.LoginCredentials;
import com.ensa.services.StudentService;
import com.ensa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/users/")
public class UserController {


    private UserService userService;
    private StudentService studentService;

    @Autowired
    public UserController(UserService userService,StudentService studentService) {
        this.userService = userService;
        this.studentService = studentService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginCredentials login) {
        String token = userService.login(login);
        return ResponseEntity.ok(token);
    }


    @PostMapping(path = "/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody User user){
        try {
            User user1 = userService.signUp(user);
            return new ResponseEntity<>(user1,HttpStatus.OK);
        }catch (Exception e){
           return ResponseEntity
                   .status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(path = "/list/{role}")
    public ResponseEntity<?> findByRole(@PathVariable String role){
        try {
            List<User> users= userService.findUsersByRole(role);
            return new ResponseEntity<>(users,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/listByNiveau/{id}")
    public ResponseEntity<?> allStudnetByNiveau(@PathVariable Long id){
        try {

            List<Student> students = studentService.getStudentByNiveau(id);
            return new ResponseEntity<>(students,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
