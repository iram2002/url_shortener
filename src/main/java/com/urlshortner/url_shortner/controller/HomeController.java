// This file checks whether the user is logged in or not.
// It sends the login status message to the index page.

package com.urlshortner.url_shortner.controller;

import com.urlshortner.url_shortner.model.User;
import com.urlshortner.url_shortner.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller // Marks this class as a Spring MVC controller
public class HomeController {

    private final UserRepository userRepository; // Repository to interact with the database

    // Constructor-based dependency injection for UserRepository
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Handles requests to the home page ('/')
     * Checks if a user is logged in and sends this information to the index page.
     *
     * @param model   Used to pass attributes to the view
     * @param session HttpSession to track user login status
     * @return index.html view name
     */
    @GetMapping("/") // Maps this method to the root URL ('/')
    public String indexPage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser"); // Retrieve logged-in user from session

        if (loggedInUser != null) {
            // If user is logged in, add their details to the model
            model.addAttribute("loggedIn", true);
            model.addAttribute("username", loggedInUser.getUsername());
        } else {
            // If no user is logged in, set loggedIn to false
            model.addAttribute("loggedIn", false);
        }

        return "index"; // Return the index.html view
    }
}
