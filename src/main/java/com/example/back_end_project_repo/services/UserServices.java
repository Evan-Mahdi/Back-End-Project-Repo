package com.example.back_end_project_repo.services;

import com.example.back_end_project_repo.models.User;
import com.example.back_end_project_repo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    //find all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //find user by id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    //add user
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // delete user by id
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}