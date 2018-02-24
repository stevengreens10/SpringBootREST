package me.stevengreen.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Configures Spring Web Security
 * Dictates which URL paths need to be authorized and session policy
 * @author Steven Green
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	/**
	 * Main configuration method for Spring Security
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors()
		.and()
		 // Disable CSRF
		.csrf().disable()
			// Authorize requests to /login and /register
			.authorizeRequests()
			.antMatchers("/user/login", "/user/register").permitAll()
			.and()
			// Authenticate all other paths
			.authorizeRequests()
			.antMatchers("/**").authenticated()
			.and()
			// Stateless session
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			// Handle logout
			.logout().logoutSuccessUrl("/").permitAll();
		
		http.headers().cacheControl();
	}
}
	
