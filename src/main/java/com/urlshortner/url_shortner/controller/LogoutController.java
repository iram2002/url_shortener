package com.urlshortner.url_shortner.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class LogoutController {

    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate(); // Logout ke liye session destroy kar raha hai
        redirectAttributes.addFlashAttribute("message", "Logout successful!");
        return "redirect:/"; // Redirect to home page
    }


}
