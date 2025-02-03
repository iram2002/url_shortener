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
        String finalAlias = customAlias;

        // Check if the alias is unique (you can check this in your repository)
        Optional<ShortUrl> existingUrl = shortUrlRepository.findByShortCode(finalAlias);

        // If the alias already exists, generate a new alias by adding random numbers
        int attempts = 0;
        while (existingUrl.isPresent() && attempts < 10) {
            finalAlias = customAlias + generateRandomNumber();  // Add a random number to the alias
            existingUrl = shortUrlRepository.findByShortCode(finalAlias);  // Check again
            attempts++;
        }

        // If still found after 10 attempts, throw an exception (optional)
        if (existingUrl.isPresent()) {
            throw new IllegalArgumentException("Unable to generate a unique alias after several attempts.");
        }

        // Create and save the short URL with the custom alias
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setLongUrl(longUrl);
        shortUrl.setShortCode(finalAlias);
        shortUrlRepository.save(shortUrl);

        return finalAlias;
    }

    // Helper method to generate a random number (used for numeric extension)
    private String generateRandomNumber() {
        Random random = new Random();
        return String.valueOf(random.nextInt(1000)); // Generates a random number between 0 and 999
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
