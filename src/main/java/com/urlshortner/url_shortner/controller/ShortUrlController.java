package com.urlshortner.url_shortner.controller;

import com.urlshortner.url_shortner.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    // Handle form submission for shortening a URL (Anonymous User URL Shortening)
    @PostMapping("/generate-url")
    public String generateUrl(@RequestParam("originalUrl") String originalUrl, Model model) {
        // Generate a short URL
        String shortCode = shortUrlService.shortenUrl(originalUrl);

        // Add the generated short URL to the model
        model.addAttribute("shortUrl", "http://localhost:8080/" + shortCode);

        return "index";  // Return to the home page with the short URL
    }

    // Handle redirecting short code to the original URL (Redirect Logic)
    @GetMapping("/{shortCode}")
    public String redirectToOriginal(@PathVariable String shortCode) {
        // Look for the long URL using the short code
        Optional<String> longUrl = shortUrlService.getLongUrl(shortCode);

        // If found, redirect to the long URL, else show an error page
        return longUrl.map(url -> "redirect:" + url).orElse("error");
    }

    // Handle custom URL shortening (for logged-in users)
    @PostMapping("/generate-custom-url")
    public String generateCustomUrl(@RequestParam("originalUrl") String originalUrl,
                                    @RequestParam("customAlias") String customAlias,
                                    Model model) {
        try {
            // Generate custom short URL (handle uniqueness check and saving in service)
            String shortCode = shortUrlService.shortenUrlWithCustomAlias(originalUrl, customAlias);

            // Add the custom short URL to the model
            model.addAttribute("shortUrl", "http://localhost:8080/" + shortCode);
        } catch (IllegalArgumentException e) {
            // If alias already exists, display error message
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "index";  // Return to the home page with the custom short URL or error message
    }
}
