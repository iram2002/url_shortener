package com.urlshortner.url_shortner.service;

import com.urlshortner.url_shortner.model.ShortUrl;
import com.urlshortner.url_shortner.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class ShortUrlService {

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    // Method to generate a short URL and save it to the database
    public ShortUrl shortenUrl(String originalUrl) {
        String shortCode = generateShortCode();
        ShortUrl shortUrl = new ShortUrl(originalUrl, shortCode);
        return shortUrlRepository.save(shortUrl);
    }

    // Simple random string generator for the short code
    private String generateShortCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortCode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            shortCode.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortCode.toString();
    }

    // Method to find URL by short code
    public ShortUrl getOriginalUrl(String shortCode) {
        return shortUrlRepository.findByShortCode(shortCode);
    }
}
