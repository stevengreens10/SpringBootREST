package me.stevengreen.springboot;

import me.stevengreen.springboot.repository.UserRepository;
import me.stevengreen.springboot.security.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Starts the SpringBoot application with main method instead of WAR file
 * Server is embedded into the application
 *
 * @author Steven Green
 */
@SpringBootApplication
public class SpringBootApp {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    /**
     * Main method for the application.
     * Starts the server
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    /**
     * Custom authentication manager
     *
     * @param builder The builder for the authentication manager
     */
    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> new CustomUserDetails(userRepository.findByUsername(username));
    }
}
