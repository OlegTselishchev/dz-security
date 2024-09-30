package com.example.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/open")
    public ResponseEntity<String> getOpen() {
        return ResponseEntity.ok("OPEN FOR NOT REGISTRY USER");
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcome() {
        return ResponseEntity.ok("WELCOME REGISTRY USER");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> getTestAdmin() {
        return ResponseEntity.ok("ADMIN");
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<String> getTestUser() {
        return ResponseEntity.ok("USER");
    }
}
