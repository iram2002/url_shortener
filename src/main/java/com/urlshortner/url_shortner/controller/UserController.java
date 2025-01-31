package com.urlshortner.url_shortner.controller;

import com.urlshortner.url_shortner.model.User;
import com.urlshortner.url_shortner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register user
    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return "User registered successfully!";
    }

    // Login user
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        // Add login logic here (e.g., compare password)
        return "Login successful!";
    }

}

