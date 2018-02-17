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
	@PostMapping("/user/login")
	public ResponseEntity<String> login() {
		return new ResponseEntity<String>("", HttpStatus.ACCEPTED);
	}
}
