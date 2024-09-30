package com.example.security.service;

import com.example.security.model.dto.AuthRequest;
import com.example.security.model.dto.AuthResponse;
import com.example.security.model.dto.RegRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    AuthResponse register(RegRequest request);
    AuthResponse authenticate(AuthRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
