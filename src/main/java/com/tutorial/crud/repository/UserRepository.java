package com.tutorial.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.crud.model.User;

public interface UserRepository  extends JpaRepository<User, Long>{

    boolean existsByEmail(String email);
    List<User> findByEmail(String email);
}
