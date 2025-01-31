package com.urlshortner.url_shortner.controller;

import com.urlshortner.url_shortner.model.User;
import com.urlshortner.url_shortner.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        // Ensure the user does not already exist
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "signup";
        }

        // Create new user
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password); // No hashing for now
        userRepository.save(user);

        return "redirect:/login"; // Redirect to login page after signup
    }

    // Handle Login Form Submission
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) { // Plain text password check
                session.setAttribute("loggedInUser", user);
                return "redirect:/"; // Redirect to home page after login
            } else {
                model.addAttribute("error", "Invalid credentials");
                return "login";
            }
        }

        model.addAttribute("error", "User not found");
        return "login";
    }
    @GetMapping("/auth/check-login")
    @ResponseBody
    public Map<String, Object> checkLogin(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            response.put("loggedIn", true);
            response.put("username", loggedInUser.getUsername());
        } else {
            response.put("loggedIn", false);
        }
        return response;
    }

}
