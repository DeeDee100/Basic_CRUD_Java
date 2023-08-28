package com.tutorial.crud.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorial.crud.model.User;
import com.tutorial.crud.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly=true)
    public List<User> findAll(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        User result = userRepository.findByEmail(email).get(0);
        return result;
    }

    public User create(User userToCreate) {
        String userEmail = userToCreate.getEmail();

        if (userRepository.existsByEmail(userEmail)) {
            throw new IllegalArgumentException("This user already exists");
        }
        
        userRepository.save(userToCreate);
        return userToCreate;
    }

    public void updateUser(User userToUpdate) {
        
        List<User> userData = userRepository.findByEmail(userToUpdate.getEmail());
        if (userData.isEmpty()) {
            throw new NoSuchElementException("User " + userToUpdate.getName() + " does not exists");
        }
            User newUser = userData.get(0);
            newUser.setEmail(userToUpdate.getEmail());
            newUser.setName(userToUpdate.getName());
            newUser.setPhone(userToUpdate.getPhone());
            userRepository.save(newUser);
    }
}
