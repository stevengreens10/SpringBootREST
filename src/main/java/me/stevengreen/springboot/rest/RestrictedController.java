package me.stevengreen.springboot.rest;

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

}
