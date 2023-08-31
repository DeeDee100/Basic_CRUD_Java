package com.tutorial.crud.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.crud.model.Movie;

public interface MovieRepository  extends JpaRepository<Movie, Long>{

}
