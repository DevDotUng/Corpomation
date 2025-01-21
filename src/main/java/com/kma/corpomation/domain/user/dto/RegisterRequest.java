package com.kma.corpomation.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private UserRole userRole;
}
