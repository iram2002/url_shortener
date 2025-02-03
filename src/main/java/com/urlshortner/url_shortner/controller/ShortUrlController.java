package com.urlshortner.url_shortner.controller;

import com.urlshortner.url_shortner.service.ShortUrlService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for URL shortening and redirection.
 */
@Controller
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    /**
     * Handles shortening a URL (anonymous users).
     * @param originalUrl The original URL.
     * @param model Spring MVC model.
     * @return View name (index).
     */
    @PostMapping("/generate-url")
    public String generateUrl(@RequestParam("originalUrl") String originalUrl, Model model) {
        String shortCode = shortUrlService.shortenUrl(originalUrl); // Generate short code.
        model.addAttribute("shortUrl", "http://localhost:8080/" + shortCode); // Add short URL to model.
        return "index"; // Return index view.
    }

    /**
     * Redirects to the original URL.
     * @param shortCode The short code.
     * @return Redirect or error page.
     */
    @GetMapping("/{shortCode}")
    public String redirectToOriginal(@PathVariable String shortCode) {
        Optional<String> longUrl = shortUrlService.getLongUrl(shortCode); // Get original URL.
        return longUrl.map(url -> "redirect:" + url).orElse("error"); // Redirect or return error.
    }

    /**
     * Handles custom URL shortening (logged-in users).
     * @param originalUrl The original URL.
     * @param customAlias The custom alias.
     * @param model Spring MVC model.
     * @param session HTTP session.
     * @return View name (index).
     */
    @PostMapping("/generate-custom-url")
    public String generateCustomUrl(@RequestParam("originalUrl") String originalUrl,
                                    @RequestParam("customAlias") String customAlias,
                                    Model model,
                                    HttpSession session) {
        try {
            String shortCode = shortUrlService.shortenUrlWithCustomAlias(originalUrl, customAlias); // Generate custom short URL.
            model.addAttribute("shortUrl", "http://localhost:8080/" + shortCode); // Add short URL to model.
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage()); // Handle duplicate alias.
        }

        Object loggedInUser = session.getAttribute("loggedInUser"); // Get logged-in user.
        if (loggedInUser != null) {
            model.addAttribute("loggedIn", true);
            model.addAttribute("username", ((com.urlshortner.url_shortner.model.User) loggedInUser).getUsername()); // Add user info to model.
        } else {
            model.addAttribute("loggedIn", false); // User not logged in.
        }

        return "index"; // Return index view.
    }
}