package me.stevengreen.springboot.rest.user.register;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
//import static io.restassured.module.mockmvc.matcher.RestAssuredMockMvcMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.springframework.http.HttpStatus;

/**
 * Tests the register REST endpoint using RestAsssured
 * @author Steven Green
 *
 */
public class RegistrationControllerTest {
	
	/**
	 * Tests the /login endpoint
	 */
	@Test
	public void testRegistrationEndpoint() {
		// Tests post with no params
		given()
			.standaloneSetup(new RegistrationController())
		.when()
			.post("/user/login")
		.then()
			.statusCode(HttpStatus.UNAUTHORIZED.value())
			.body("message", equalTo("No username or email provided"));
		
		//Tests with empty username
		given()
			.standaloneSetup(new RegistrationController())
			.param("username", "")
			.param("password", "")
			.param("email", "test@betterpack.org")
		.when()
			.post("/user/login")
		.then()
			.statusCode(HttpStatus.UNAUTHORIZED.value())
			.body("message", equalTo("Username invalid"));
		
		//Tests with empty email
		given()
			.standaloneSetup(new RegistrationController())
			.param("username", "username")
			.param("password", "")
			.param("email", "")
		.when()
			.post("/user/login")
		.then()
			.statusCode(HttpStatus.UNAUTHORIZED.value())
			.body("message", equalTo("Email invalid"));
		
		//Tests with empty password
				given()
					.standaloneSetup(new RegistrationController())
					.param("username", "username")
					.param("password", "")
					.param("email", "test@betterpack.org")
				.when()
					.post("/user/login")
				.then()
					.statusCode(HttpStatus.UNAUTHORIZED.value())
					.body("message", equalTo("Password invalid"));
	}
	
}
