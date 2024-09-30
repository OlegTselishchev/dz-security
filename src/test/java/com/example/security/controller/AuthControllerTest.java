package com.example.security.controller;

import com.example.security.auditing.ApplicationAuditAware;
import com.example.security.config.AppConfig;
import com.example.security.config.LogoutService;
import com.example.security.config.SecurityConf;
import com.example.security.filter.JwtAuthFilter;
import com.example.security.reposiroty.TokenRepository;
import com.example.security.reposiroty.UserRepository;
import com.example.security.security.JwtService;
import com.example.security.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import({ApplicationAuditAware.class, JwtAuthFilter.class, JwtService.class, AuthServiceImpl.class, AppConfig.class, LogoutService.class, SecurityConf.class})
public class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private TokenRepository tokenRepository;

    @Test
    @WithAnonymousUser
    void registry_success() throws Exception {
        this.mvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"user\", \"login\": \"user\", \"password\": \"user\", \"role\": \"USER\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void authenticate_accessDenied() throws Exception {
        this.mvc.perform(post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"login\": \"user\", \"password\": \"user\"}"))
                        .andExpect(status().is4xxClientError());
    }
}
