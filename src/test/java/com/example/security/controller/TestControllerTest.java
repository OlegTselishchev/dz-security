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
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TestController.class)
@Import({ApplicationAuditAware.class, JwtAuthFilter.class, JwtService.class, AuthServiceImpl.class, AppConfig.class, LogoutService.class, SecurityConf.class})
public class TestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private TokenRepository tokenRepository;

    @Test
    @WithAnonymousUser
    void testController_getOpen_WithoutAuthorization_Success() throws Exception {
        this.mvc.perform(get("/api/v1/test/open"))
                .andExpect(status().isOk())
                .andExpect(content().string("OPEN FOR NOT REGISTRY USER"));
    }

    @Test
    @WithAnonymousUser
    void testController_getWelcome_WithoutAuthorization_Error() throws Exception {
        this.mvc.perform(get("/api/v1/test/welcome"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser
    void testController_getWelcome_AnyAuthorization_Success() throws Exception {
        this.mvc.perform(get("/api/v1/test/welcome"))
                .andExpect(status().isOk())
                .andExpect(content().string("WELCOME REGISTRY USER"));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void testController_getTestUser_AnyAuthorization_Success() throws Exception {
        this.mvc.perform(get("/api/v1/test/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("USER"));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void testController_getTestAdmin_WithoutAuthorization_Error() throws Exception {
        this.mvc.perform(get("/api/v1/test/admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testController_getTestAdmin_Authorization_Success() throws Exception {
        this.mvc.perform(get("/api/v1/test/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("ADMIN"));
    }
}
