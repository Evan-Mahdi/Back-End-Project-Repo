package com.example.back_end_project_repo.services;

import com.example.back_end_project_repo.models.User;
import com.example.back_end_project_repo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        String password = user.getPassword();
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be Empty");
        }
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        ;
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            existingUser.setPassword(encodedPassword);
        }
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    // delete user by id
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));
        return passwordEncoder.matches(password, user.getPassword());

    }

}