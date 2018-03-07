package me.stevengreen.springboot.rest.user.register;

import me.stevengreen.springboot.document.UserDocument;
import me.stevengreen.springboot.document.UserRole;
import me.stevengreen.springboot.repository.UserRepository;
import me.stevengreen.springboot.rest.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Service for registering users
 *
 * @author Steven Green
 */
@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for dependency injection
     *
     * @param userRepository  UserRepository instance
     * @param passwordEncoder PasswordEncoder instance
     */
    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a user
     *
     * @param user The user to register
     * @return Whether registration was successful or not
     */
    public boolean register(User user) {
        UserDocument dbUser = userRepository.findByUsername(user.getUsername());

        if (dbUser != null) return false;

        if (user.getUsername() != null && user.getUsername().length() > 0
                && user.getPassword() != null && user.getPassword().length() > 0) {

            String passHash = passwordEncoder.encode(user.getPassword());
            userRepository.insert(new UserDocument(user.getUsername(), passHash, Arrays.asList(UserRole.USER)));
            return true;
        }

        return false;
    }

}
