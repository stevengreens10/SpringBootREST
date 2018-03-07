package me.stevengreen.springboot.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Configures global authentication manager builder
 *
 * @author Steven Green
 */
@Configuration
public class GlobalAuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

    private final UserDetailsService userDetailsServices;

    /**
     * Constructor for dependency injection
     *
     * @param userDetailsServices The user details service instance
     */
    @Autowired
    public GlobalAuthenticationConfig(UserDetailsService userDetailsServices) {
        this.userDetailsServices = userDetailsServices;
    }

    /**
     * Initial configuration of authentication manager builder
     *
     * @param auth AuthenticationMangerBuilder object
     * @throws Exception if an error occurs when setting user details service
     */
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServices);
    }
}
