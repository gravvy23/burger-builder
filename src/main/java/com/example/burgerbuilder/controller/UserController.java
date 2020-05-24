package com.example.burgerbuilder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.burgerbuilder.model.User;
import com.example.burgerbuilder.repository.UserRepository;
import com.example.burgerbuilder.exception.ResourceNotFoundException;
 
@RestController
@RequestMapping("/api")
public class UserController {
     
    @Autowired
    private UserRepository userRepository;
     
    @GetMapping("/users")
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
     
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id) {
        User entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + id));;
        return entity;
    }
    
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
    	return userRepository.save(user);
    }
    
    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable String userId,
                                   @RequestBody User userRequest) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setName(userRequest.getName());
                    user.setSurname(userRequest.getSurname());
                    user.setMail(userRequest.getMail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + userId));
    }
    
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + userId));
    }
}