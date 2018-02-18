package me.stevengreen.springboot.rest.user.register;

import me.stevengreen.springboot.rest.user.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for REST registration endpoints
 * @author Steven Green
 */
@RestController
public class RegistrationController {

	/**
	 * REST endpoint for user registration
	 * @param user User object containing information for registration
	 * @return Response Entity indicating if the login was successful or not
	 */
	@PostMapping(value = "/user/register", produces = "application/json")
	public ResponseEntity<RegistrationResponse> register(@RequestBody User user) {
		RegistrationResponse response = new RegistrationResponse("Message");
		return new ResponseEntity<RegistrationResponse>(response, HttpStatus.ACCEPTED);
	}
	
	/**
	 * Inner class for the response to the registration endpoint
	 * @author Steven Green
	 *
	 */
	@SuppressWarnings("unused")
	private class RegistrationResponse {
		private String message;

		public RegistrationResponse(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
