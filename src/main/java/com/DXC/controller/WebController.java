package com.DXC.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.DXC.model.User;
import com.DXC.service.UserService;

@Controller
@RequestMapping("/api")
public class WebController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	UserService userservice;
	
	@GetMapping("/login")
    public String login() {
        logger.debug("Handling request for /login");
        return "forward:/api/login";
       // return "login"; 
    }
	
	@GetMapping("/welcome")
    public String showWelcomePage(@RequestParam("message") String message, Model model) {
        model.addAttribute("message", message); // Pass the welcome message to the Thymeleaf template
        return "welcome"; // Return the name of the Thymeleaf template (welcome.html)
    }

    @GetMapping("/restricted")
    public String restricted() {
        return "restricted"; 
    }

    public String getErrorPath() {
        return "/error"; 
    }
}


