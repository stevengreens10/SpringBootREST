package me.stevengreen.springboot.rest.user.register;

import me.stevengreen.springboot.rest.user.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RegistrationService registrationService;

    /**
	 * REST endpoint for user registration
	 * @param user User object containing information for registration
	 * @return Response Entity indicating if the login was successful or not
	 */
	@PostMapping(value = "/user/register", produces = "application/json")
	public ResponseEntity<RegistrationResponse> register(@RequestBody User user) {
        RegistrationResponse response;
        HttpStatus status;
        if (registrationService.register(user)) {
            response = new RegistrationResponse("Valid registration");
            status = HttpStatus.OK;
        } else {
            response = new RegistrationResponse("Invalid registration.");
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<>(response, status);
	}
	
	/**
	 * Inner class for the response to the registration endpoint
	 * @author Steven Green
	 *
	 */
	@SuppressWarnings("unused")
	private class RegistrationResponse {
		private String status;

		public RegistrationResponse(String status) {
			setStatus(status);
		}

		public String getMessage() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	}
}
