package com.urlshortner.url_shortner.service;

import com.urlshortner.url_shortner.model.User;
import com.urlshortner.url_shortner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for user-related operations.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Repository for User entities.

    /**
     * Registers a new user.
     * @param user The User object to register.
     * @return The registered User object.
     */
    public User registerUser(User user) {
        // Add validation here (e.g., check if username or email is already taken).
        return userRepository.save(user); // Save the user to the database.
    }

    /**
     * Authenticates a user.
     * @param username The username.
     * @param password The password.
     * @return True if authentication is successful, false otherwise.
     */
    public boolean authenticate(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username); // Find user by username.
        if (optionalUser.isPresent()) {
            User user = optionalUser.get(); // Get the User object.
            return user.getPassword().equals(password); // Compare passwords.
        }
        return false; // Return false if user not found.
    }
}