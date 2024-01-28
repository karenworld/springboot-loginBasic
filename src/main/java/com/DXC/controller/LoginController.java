package com.DXC.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DXC.model.User;
import com.DXC.service.UserService;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	 @GetMapping("/login")
	  public String login() {
		 logger.info("Handling request for /login");
	    return "login";
	  }
	
	@Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // You can customize the response based on successful login
            String welcomeMessage = "Welcome " + user.getName() + "! Role: " + user.getRole();
            return ResponseEntity.ok(welcomeMessage);
        } else {
            // Return a 401 Unauthorized status for invalid credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid userid or password");
        }
    }

    @GetMapping("/welcome/{username}")
    public ResponseEntity<String> welcome(@PathVariable String username) {
        User user = userService.getUserByUsername(username);

        if (user != null) {
            String welcomeMessage = "Welcome " + user.getName() + "! Role: " + user.getRole();
            return ResponseEntity.ok(welcomeMessage);
        } else {
            // Return a 404 Not Found status if the user is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/restricted")
    public ResponseEntity<String> restricted() {
        // Check user role and allow access only to managers
        // Implement your logic here

        // Return a 403 Forbidden status for unauthorized access
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Forbidden");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        // Implement logout logic

        // You can customize the response based on successful logout
        return ResponseEntity.ok("Logout successful");
    }
    
  }