package com.tutorial.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.crud.model.User;

public interface UserRepository  extends JpaRepository<User, Long>{

    boolean existsByEmail(String email);
}
