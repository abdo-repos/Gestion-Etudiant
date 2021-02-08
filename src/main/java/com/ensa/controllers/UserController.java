package com.ensa.controllers;

import com.ensa.entity.User;
import com.ensa.helpers.LoginCredentials;
import com.ensa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/users/")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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

}
