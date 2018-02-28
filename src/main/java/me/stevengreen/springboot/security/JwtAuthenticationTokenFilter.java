package me.stevengreen.springboot.security;

import me.stevengreen.springboot.security.model.JwtAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Chooses which URLs require JWT Authentication and attempts authentication
 *
 * @author Steven Green
 */
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    private static final String PROCESS_URL = "/**";
    private static final List<String> SKIPPED_URLS = Arrays.asList("/user/login", "/user/register");

    /**
     * Constructs the filter with the URLs not to authenticate
     */
    public JwtAuthenticationTokenFilter() {
        super(new SkipPathRequestMatcher(SKIPPED_URLS, PROCESS_URL));
    }

    /**
     * Attempts authentication with the JWT token
     * The Token should be in the Authorization header in the following format:
     * "Token {token}"
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException,
            IOException {

        Cookie[] cookies = request.getCookies();

        Cookie tokenCookie = null;

        for (Cookie c : cookies) {
            if (c.getName().equals("token")) {
                tokenCookie = c;
            }
        }

        if (tokenCookie == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token is missing");
            return null;
        }

        String authenticationToken = tokenCookie.getValue();

        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);

        Authentication authentication;

        try {
            authentication = getAuthenticationManager().authenticate(token);
        } catch (BadCredentialsException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token is invalid");
            return null;
        }

        return authentication;
    }

    /**
     * Handles successful authentication
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }


}
