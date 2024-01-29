package com.DXC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DXC.model.User;
import com.DXC.repository.UserRepository;

import jakarta.annotation.PostConstruct;


@Service
public class UserService {
	
    private UserRepository userRepository;
	
//	@PostConstruct
//    public void init() {
//        // Initialize users in the database
//        userRepository.save(new User("abc", "user", "password", "USER"));
//        userRepository.save(new User("def", "manager", "password", "MANAGER"));
//    }
		

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
