package com.tutorial.crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorial.crud.model.Movie;
import com.tutorial.crud.repository.MovieRepository;


@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly=true)
    public List<Movie> findAll(){
        List<Movie> movieList = movieRepository.findAll();
        return movieList;
    }

    public Movie create(Movie movieToCreate) {
        Long userId = movieToCreate.getId();
        // TODO: Verificar se user existe
        movieRepository.save(movieToCreate);
        return movieToCreate;
    }
}
