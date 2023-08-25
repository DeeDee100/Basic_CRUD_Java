package com.tutorial.crud.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tutorial.crud.model.Movie;
import com.tutorial.crud.services.MovieService;

@RestController
@RequestMapping(value = "/")
public class MovieController {
    
    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAll(){
        return movieService.findAll();
    }

    @PostMapping(value = "/createMovie")
    public ResponseEntity<Movie> create(@RequestBody Movie movieToCreate){
        var movieCreated = movieService.create(movieToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(movieCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(movieCreated);
    }
}
