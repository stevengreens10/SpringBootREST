package me.stevengreen.springboot.rest.user.login;

import me.stevengreen.springboot.rest.user.JwtService;
import me.stevengreen.springboot.rest.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for REST login endpoints
 * @author Steven Green
 */
@RestController
public class LoginController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private LoginService loginService;

    /**
	 * REST endpoint for user authentication
	 * @param user User object containing authentication information
	 * @return Response Entity indicating if the login was successful or not
	 * If the login was successful, a JWT token will be returned.
	 */
	@PostMapping(value = "/user/login", produces = "application/json")
	public ResponseEntity<LoginResponse> login(@RequestBody User user) {
        LoginResponse response;
        HttpStatus status;
        if (loginService.login(user)) {
            response = new LoginResponse("Token: " + jwtService.generate(user));
            status = HttpStatus.OK;
        } else {
            response = new LoginResponse("Invalid login.");
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<>(response, status);
	}
	
	/**
	 * Inner login response class
	 * @author Steven Green
	 *
	 */
	@SuppressWarnings("unused")
	private class LoginResponse {

		private String message;
		
		public LoginResponse(String message) {
			setMessage(message);
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}
	
}
