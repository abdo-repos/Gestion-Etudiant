package com.ensa.controllers;

import com.ensa.entity.AllUser;
import com.ensa.services.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/users/")
public class AllUserController {


    private AllUserService userService;

    @Autowired
    public AllUserController(AllUserService userService){
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody AllUser user){
        try {
            AllUser savedUser = userService.addUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
