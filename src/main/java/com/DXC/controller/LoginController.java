package com.DXC.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.DXC.model.User;
import com.DXC.service.UserService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
public class LoginController implements ErrorController{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // Check user role
            if ("MANAGER".equals(user.getRole())) {
                // If the user has a manager role, include a link to the restricted webpage
                String welcomeMessage = "Welcome " + user.getName() + "! Role: " + user.getRole()
                        + ". You can access the restricted webpage: /api/restricted";
                return ResponseEntity.ok(welcomeMessage);
            } else {
                // For other roles, provide a standard welcome message
                String welcomeMessage = "Welcome " + user.getName() + "! Role: " + user.getRole();
                return ResponseEntity.ok(welcomeMessage);
            }
        } else {
            // Return a 401 Unauthorized status for invalid credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid userid or password");
        }
    }

    @GetMapping("/restricted")
    public ResponseEntity<String> restricted(@RequestHeader(value = "role", defaultValue = "USER") String role) {
        // Check user role and allow access only to managers
        if ("MANAGER".equals(role)) {
            return ResponseEntity.ok("Welcome to the restricted webpage. Only managers can access this.");
        } else {
            // Return a 403 Forbidden status for unauthorized access
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Forbidden");
        }
    }
}

    