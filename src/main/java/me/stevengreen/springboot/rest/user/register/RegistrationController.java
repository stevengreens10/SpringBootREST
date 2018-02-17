package me.stevengreen.springboot.rest.user.register;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for REST registration endpoints
 * @author Steven Green
 */
@RestController
public class RegistrationController {

	/**
	 * REST endpoint for user registration
	 * @return Response Entity indicating if the login was successful or not
	 */
	@PostMapping("/user/register")
	public ResponseEntity<String> register() {
		return new ResponseEntity<String>("", HttpStatus.ACCEPTED);
	}
}
