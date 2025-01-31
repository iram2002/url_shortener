package com.urlshortner.url_shortner.controller;

import com.urlshortner.url_shortner.model.User;
import com.urlshortner.url_shortner.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Check if user is logged in and send this info to index
    @GetMapping("/")
    public String indexPage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("loggedIn", true);
            model.addAttribute("username", loggedInUser.getUsername());
        } else {
            model.addAttribute("loggedIn", false);
        }

        return "index";
    }
}