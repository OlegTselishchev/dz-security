package com.example.security.model.dto;

import com.example.security.model.entity.enams.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegRequest {
    private String name;
    private String login;
    private String password;
    private Role role;
}
