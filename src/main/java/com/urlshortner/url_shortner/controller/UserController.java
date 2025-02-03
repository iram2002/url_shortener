package com.urlshortner.url_shortner.controller;

import com.urlshortner.url_shortner.model.User;
import com.urlshortner.url_shortner.repository.UserRepository;
import com.urlshortner.url_shortner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for user-related operations.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService; // Service for user operations.

    @Autowired
    private UserRepository userRepository; // Repository for user data.

    /**
     * Registers a new user.
     * @param user The user object to register.
     * @return A success message.
     */
    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user); // Register the user using the service.
        return "User registered successfully!"; // Return success message.
    }
}