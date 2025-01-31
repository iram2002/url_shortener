package com.urlshortner.url_shortner.service;

import com.urlshortner.url_shortner.model.ShortUrl;
import com.urlshortner.url_shortner.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Random;

@Service
public class ShortUrlService {

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    // Method to generate a short URL and save it to the database
    public String shortenUrl(String longUrl) {
        String shortCode = generateShortCode(); // Generate a random short code

        // Save to database
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setLongUrl(longUrl);
        shortUrl.setShortCode(shortCode);
        shortUrlRepository.save(shortUrl);

        return shortCode;
    }

    // Method to get the original URL by short code
    public Optional<String> getLongUrl(String shortCode) {
        Optional<ShortUrl> optionalShortUrl = shortUrlRepository.findByShortCode(shortCode);
        //Optional<ShortUrl> ShortUrl = shortUrlRepository.findByShortCode(shortCode);
        return optionalShortUrl.map(ShortUrl::getLongUrl);
    }
    public String shortenUrlWithCustomAlias(String longUrl, String customAlias) {
        // Check if the alias is unique (you can check this in your repository)
        Optional<ShortUrl> existingUrl = shortUrlRepository.findByShortCode(customAlias);

        if (existingUrl.isPresent()) {
            throw new IllegalArgumentException("Custom alias already exists.");
        }

        // Create and save the short URL with the custom alias
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setLongUrl(longUrl);
        shortUrl.setShortCode(customAlias);
        shortUrlRepository.save(shortUrl);

        return customAlias;
    }


    // Helper method to generate a random 6-character short code
    private String generateShortCode() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder shortCode = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            shortCode.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortCode.toString();
    }
}
