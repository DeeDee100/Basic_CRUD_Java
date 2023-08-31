package com.tutorial.crud.controllers;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tutorial.crud.model.Movie;
import com.tutorial.crud.model.User;
import com.tutorial.crud.services.MovieService;
import com.tutorial.crud.services.UserService;

@RestController
@RequestMapping(path = "/")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/movies")
    public List<Movie> getAll() {
        return movieService.findAll();
    }

    @PostMapping(path = "/users/{id}/movie")
    public ResponseEntity<Object> createMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Optional<User> optional_user = userService.findById(id);
        if (!optional_user.isPresent()) {
            throw new NoSuchElementException("id: " + id);
        }

        User user = optional_user.get();

        movie.setUser(user);
        movieService.create(movie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(movie.getId())
        .toUri();
        // returns the location of the created post
        return ResponseEntity.created(location).build();

    }
}
