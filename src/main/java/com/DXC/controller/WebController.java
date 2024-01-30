package com.DXC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.DXC.service.UserService;

@Controller
public class WebController {

	 @Autowired
	 private UserService userService;
	
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/welcome/{username}")
    public String welcome(@PathVariable String username, Model model) {
        com.DXC.model.User user = userService.getUserByUsername(username);

        if (user != null) {
            // Add user information to the model
            model.addAttribute("user", user);
            return "welcome";
        } else {
            // Return an error page if the user is not found
            return "error";
        }
    }
}