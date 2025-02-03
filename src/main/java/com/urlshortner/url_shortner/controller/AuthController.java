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

@Controller // Marks this class as a Spring MVC Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository; // Injects UserRepository to interact with the database

    /**
     * Displays the login page.
     * Ensures that the "errorMessage" attribute is always available to prevent null errors in the UI.
     *
     * @param model Model object to pass data to the view.
     * @return ("login.html").
     */
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("errorMessage", "");
        return "login";
    }

    /**
     * Displays the signup page.
     *
     * @param model Model object to pass data to the view.
     * @return ("signup.html").
     */
    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        return "signup";
    }

    /**
     * Handles user registration.
     *
     * @param username The desired username.
     * @param email User's email.
     * @param password User's password (Note: No hashing implemented, which is a security risk).
     * @param model Model object to pass error messages if signup fails.
     * @return Redirects to login page if successful, otherwise reloads the signup page with an error message.
     */
    @PostMapping("/signup")
    public String handleSignup(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model) {
        // Check if username is already taken
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("errorMessage", "Username already exists");
            return "signup";
        }

        // Create a new user and save to the database
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password); // TODO: Implement password hashing for security
        userRepository.save(user);

        return "redirect:/login"; // Redirects to login page after successful signup
    }

    /**
     * Handles user login authentication.
     *
     * @param username Entered username.
     * @param password Entered password.
     * @param model Model object to pass error messages if login fails.
     * @param session HTTP session to store logged-in user data.
     * @return Redirects to the homepage if login is successful, otherwise reloads login page with an error message.
     */
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model,
                              HttpSession session) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) { // TODO: Implement password hashing comparison
                session.setAttribute("loggedInUser", user);
                return "redirect:/"; // Redirects to home page after successful login
            } else {
                model.addAttribute("errorMessage", "Invalid credentials");
                return "login";
            }
        }

        model.addAttribute("error", "User not found");
        return "login";
    }

    /**
     * API to check if a user is logged in.
     * This is useful for frontend applications that need to verify authentication status.
     *
     * @param session HTTP session to check user login status.
     * @return JSON response indicating if a user is logged in and their username if available.
     */
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
