package me.stevengreen.springboot.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints that require valid JWT
 */
@RestController
public class RestrictedController {

    @GetMapping("/hello")
    public String hello() {
        return "You are successfully authenticated!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Authentication admin() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
