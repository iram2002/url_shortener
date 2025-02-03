package com.urlshortner.url_shortner.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for handling user logout.
 */
@Controller
@RequestMapping("/")
public class LogoutController {

    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate(); // Destroy the user's session.
        redirectAttributes.addFlashAttribute("message", "Logout successful!"); // Add a success message.
        return "redirect:/"; // Redirect to the home page.
    }

}