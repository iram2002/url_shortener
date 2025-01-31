package com.urlshortner.url_shortner.repository;

import com.urlshortner.url_shortner.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
   // ShortUrl findByShortCode(String shortCode);
    Optional<ShortUrl> findByShortCode(String shortCode);
}
