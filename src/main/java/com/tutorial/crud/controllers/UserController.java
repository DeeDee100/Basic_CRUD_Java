package com.tutorial.crud.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tutorial.crud.model.User;
import com.tutorial.crud.services.UserService;


@RestController
@RequestMapping(value = "/")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.findAll();
    }

    @GetMapping(value = "/user/{email}")
    public User getById(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity<User> create(@RequestBody User userToCreate){
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }

    @PutMapping(path = "update/{email}")
    public ResponseEntity<User> update(@RequestBody User userToUpdate) {
        userService.updateUser(userToUpdate);
        return ResponseEntity.ok().body(userToUpdate);
    }

    @DeleteMapping(path = "/{email}")
    public ResponseEntity<User> delete(@PathVariable String email) {
        userService.delete(email);
        return ResponseEntity.noContent().build();
    }
}
