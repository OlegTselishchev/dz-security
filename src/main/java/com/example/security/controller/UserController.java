package com.example.security.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User")
public class UserController {
    @GetMapping
    public String get() {
        return "GET:: user controller";
    }
    @PostMapping
    public String post() {
        return "POST:: user controller";
    }
    @PutMapping
    public String put() {
        return "PUT:: user controller";
    }
    @DeleteMapping
    public String delete() {
        return "DELETE:: user controller";
    }
}
