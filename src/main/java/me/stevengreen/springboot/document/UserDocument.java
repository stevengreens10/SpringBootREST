package me.stevengreen.springboot.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Object stored in MongoDB
 * @author Steven Green
 *
 */
@Document
public class UserDocument {
	
	@Id
	private String username;
	private String passwordHash;
    private List<UserRole> roles;

    public UserDocument() {
    }

    public UserDocument(String username, String passHash, List<UserRole> roles) {
        this.username = username;
        this.passwordHash = passHash;
        this.roles = roles;
    }

    /**
	 * Gets the user's username
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the user's username
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the user's password hash
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * Sets the user's password hash
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

    public List<UserRole> getRoles() {
        return roles;
    }
}
