package me.stevengreen.springboot.repository;

import me.stevengreen.springboot.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoDB repository for user information
 *
 * @author Steven Green
 */
public interface UserRepository extends MongoRepository<UserDocument, String> {

    /**
     * Query MongoDB for the user with the unique username
     *
     * @param username The user's username
     * @return The user with that username
     */
    UserDocument findByUsername(String username);
}
