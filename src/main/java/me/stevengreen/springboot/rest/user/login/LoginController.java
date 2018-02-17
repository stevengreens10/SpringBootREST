package me.stevengreen.springboot.rest.user.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for REST login endpoints
 * @author Steven Green
 */
@RestController
public class LoginController {

	/**
	 * REST endpoint for user authentication
	 * @return Response Entity indicating if the login was successful or not
	 * If the login was successful, a JWT token will be returned.
	 */
	@PostMapping(value = "/user/login", produces = "application/json")
	public ResponseEntity<LoginResponse> login() {
		LoginResponse response = new LoginResponse("No username provided");
		return new ResponseEntity<LoginResponse>(response, HttpStatus.UNAUTHORIZED);
	}
	
	/**
	 * Inner login response class
	 * @author Steven Green
	 *
	 */
	@SuppressWarnings("unused")
	private class LoginResponse {

		private String message;
		private String token;
		
		public LoginResponse(String message) {
			this(message, null);
		}
		
		public LoginResponse(String message, String token) {
			setMessage(message);
			setToken(token);
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	}
	
}
