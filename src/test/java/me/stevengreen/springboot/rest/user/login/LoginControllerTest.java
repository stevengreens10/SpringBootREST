package me.stevengreen.springboot.rest.user.login;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
//import static io.restassured.module.mockmvc.matcher.RestAssuredMockMvcMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.springframework.http.HttpStatus;

/**
 * Tests the login REST endpoint using RestAsssured
 * @author Steven Green
 *
 */
public class LoginControllerTest {
	
	/**
	 * Tests the /login endpoint
	 */
	@Test
	public void testLoginEndpoint() {
		// Tests post with no params
		given()
			.standaloneSetup(new LoginController())
		.when()
			.post("/user/login")
		.then()
			.statusCode(HttpStatus.UNAUTHORIZED.value())
			.body("message", equalTo("No username provided"));
		
		//Tests with empty params
		given()
			.standaloneSetup(new LoginController())
			.param("username", "")
			.param("password", "")
		.when()
			.post("/user/login")
		.then()
			.statusCode(HttpStatus.UNAUTHORIZED.value())
			.body("message", equalTo("Username or password incorrect"));
	}
	
}
