package me.stevengreen.springboot.config;

import me.stevengreen.springboot.security.JwtAuthenticationEntryPoint;
import me.stevengreen.springboot.security.JwtAuthenticationProvider;
import me.stevengreen.springboot.security.JwtAuthenticationTokenFilter;
import me.stevengreen.springboot.security.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

/**
 * Configures Spring web security to use JSON Web Tokens
 *
 * @author Steven Green
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * Bean for getting an instance of the authentication manager
     *
     * @return The authentication manager
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    /**
     * Bean for getting an instance of the JwtAuthenticationTokenFilter.
     * The token filter is reponsible for choosing the paths that need JWT authorization
     *
     * @return The JWT Token filter
     */
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();

        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;
    }

    /**
     * Main configuration method for Spring Security
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF
        http.cors()
                .and()
                .csrf().disable()
                // Authorize requests to /auth/anything
                .authorizeRequests()
                .antMatchers("/auth/**", "/register").permitAll()
                .and()
                // Authenticate all other paths
                .authorizeRequests()
                .antMatchers("/**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                // Stateless session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Middleware before the controllers are processed
        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.headers().cacheControl();
    }

    /**
     * Configures Cross Origin Requests
     *
     * @return Cors Configuration
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
