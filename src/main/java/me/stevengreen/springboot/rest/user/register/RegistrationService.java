package me.stevengreen.springboot.rest.user.register;

import me.stevengreen.springboot.document.UserDocument;
import me.stevengreen.springboot.repository.UserRepository;
import me.stevengreen.springboot.rest.user.User;
import me.stevengreen.springboot.security.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for registering users
 */
@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    public boolean register(User user) {
        UserDocument dbUser = userRepository.findByUsername(user.getUsername());

        if (dbUser != null) return false;

        if (user.getUsername() != null && user.getUsername().length() > 0
                && user.getPassword() != null && user.getPassword().length() > 0) {

            int salt = (int) Math.round(Math.random() * 10000);
            String passHash = Hashing.hash(user.getPassword(), salt);
            userRepository.insert(new UserDocument(user.getUsername(), passHash, salt));
            return true;
        }

        return false;
    }

}
