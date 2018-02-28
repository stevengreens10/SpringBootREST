package me.stevengreen.springboot.rest.user.login;

import me.stevengreen.springboot.document.UserDocument;
import me.stevengreen.springboot.repository.UserRepository;
import me.stevengreen.springboot.rest.user.User;
import me.stevengreen.springboot.security.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for determining if a login is successful
 */
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(User user) {

        String unhashedPw = user.getPassword();

        UserDocument dbUser = userRepository.findByUsername(user.getUsername());

        if (dbUser != null) {
            String dbHashPw = dbUser.getPasswordHash();

            int salt = dbUser.getSalt();
            String userHashPw = Hashing.hash(unhashedPw, salt);

            return userHashPw.equals(dbHashPw);
        }

        return false;

    }

}
