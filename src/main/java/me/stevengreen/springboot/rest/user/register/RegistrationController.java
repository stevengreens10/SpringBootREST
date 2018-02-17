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
	public ResponseEntity<RegistrationResponse> register() {
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
	}
}
