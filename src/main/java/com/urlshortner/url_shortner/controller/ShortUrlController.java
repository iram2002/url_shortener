package com.urlshortner.url_shortner.controller;

import com.urlshortner.url_shortner.model.ShortUrl;
import com.urlshortner.url_shortner.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @GetMapping("/")
    public String homePage(Model model) {
        return "index"; // Return Thymeleaf template
    }

    @PostMapping("/generate-url")
    public String generateUrl(@RequestParam String originalUrl, Model model) {
        ShortUrl shortUrl = shortUrlService.shortenUrl(originalUrl);
        model.addAttribute("shortUrl", shortUrl);
        return "index"; // Return the same page with the short URL displayed
    }

    @GetMapping("/{shortCode}")
    public String redirectToOriginalUrl(@RequestParam String shortCode) {
        ShortUrl shortUrl = shortUrlService.getOriginalUrl(shortCode);
        return "redirect:" + shortUrl.getOriginalUrl(); // Redirect to the original URL
    }
}
