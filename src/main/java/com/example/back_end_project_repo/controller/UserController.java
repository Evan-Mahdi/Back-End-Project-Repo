package com.example.back_end_project_repo.controller;


import com.example.back_end_project_repo.models.User;
import com.example.back_end_project_repo.repositories.UserRepository;
import com.example.back_end_project_repo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServices userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exist");
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        if (userService.authenticateUser(user.getEmail(), user.getPassword())) {
            return ResponseEntity.ok(Collections.singletonMap("message", "login successfully"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credential");
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/home")
    public String home() {
        return "Welcome to home page";
    }
}
