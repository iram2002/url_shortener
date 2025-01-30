package com.urlshortner.url_shortner.controller;

import com.urlshortner.url_shortner.model.User;
import com.urlshortner.url_shortner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Display Login Page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Display Signup Page
    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup";
    }

    // Handle Signup Form Submission
    @PostMapping("/signup")
    public String handleSignup(@RequestParam String username, @RequestParam String email, @RequestParam String password, Model model) {
        // Add password hashing logic here
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);  // You’ll hash the password here
        userRepository.save(user);
        return "redirect:/login";
    }

    // Handle Login Form Submission
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        // Handle login logic here, verify user credentials
        return "redirect:/";
    }
}
