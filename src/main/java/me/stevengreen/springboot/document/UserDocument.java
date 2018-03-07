package me.stevengreen.springboot.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Object stored in MongoDB
 *
 * @author Steven Green
 */
@Document
public class UserDocument {

    @Id
    private String username;
    private String passwordHash;
    private List<UserRole> roles;

    /**
     * Empty default constructor
     */
    public UserDocument() {
    }

    /**
     * Creates UserDocument with relevant information
     *
     * @param username The user's username
     * @param passHash The user's hashed/encoded password
     * @param roles    List of the user's roles
     */
    public UserDocument(String username, String passHash, List<UserRole> roles) {
        this.username = username;
        this.passwordHash = passHash;
        this.roles = roles;
    }

    /**
     * Gets the user's username
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's password hash
     *
     * @return the passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the user's password hash
     *
     * @param passwordHash the passwordHash to set
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Gets the user's roles
     *
     * @return List of the user's roles
     */
    public List<UserRole> getRoles() {
        return roles;
    }
}
