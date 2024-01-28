package com.DXC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DXC.model.User;
import com.DXC.repository.UserRepository;

import jakarta.annotation.PostConstruct;


@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;
	
	@PostConstruct
    public void init() {
        // Initialize users in the database
        userRepository.save(new User("user", "password", "USER"));
        userRepository.save(new User("manager", "password", "MANAGER"));
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}