package com.urlshortner.url_shortner.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "short_urls")
public class ShortUrl {

    // ID field for the ShortUrl entity (Primary Key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The original URL provided by the user
    @Column(nullable = false) // Ensures the original URL cannot be null
    private String originalUrl;

    // The shortened URL code generated from the original URL
    @Column(unique = true, nullable = false) // Ensures this field is unique and cannot be null
    private String shortCode;

    // Timestamp of when the ShortUrl record was created
    @Column(nullable = false) // Ensures createdAt cannot be null
    private LocalDateTime createdAt = LocalDateTime.now(); // Defaults to the current time

    // Default constructor for JPA (required)
    public ShortUrl() {}

    // Parameterized constructor to set original URL and short code
    public ShortUrl(String originalUrl, String shortCode) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.createdAt = LocalDateTime.now(); // Set creation timestamp when an object is created
    }

    // Getter for ID
    public Long getId() {
        return id;
    }

    // Setter for ID (Not generally needed as ID is auto-generated)
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for original URL
    public String getOriginalUrl() {
        return originalUrl;
    }

    // Setter for original URL
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    // Getter for short URL (the shortened version)
    public String getShortCode() {
        return shortCode;
    }

    // Setter for short URL (the shortened version)
    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    // Getter for the creation timestamp
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setter for creation timestamp (generally not needed)
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Getter for original URL (renamed method for clarity as per user input)
    public String getLongUrl() {
        return originalUrl;
    }

    // Setter for original URL (renamed method for clarity as per user input)
    public void setLongUrl(String longUrl) {
        this.originalUrl = longUrl;
    }

    // Getter for short URL (renamed method for clarity as per user input)
    public String getShortUrl() {
        return shortCode;
    }

    // Setter for short URL (renamed method for clarity as per user input)
    public void setShortUrl(String shortUrl) {
        this.shortCode = shortUrl;
    }
}
