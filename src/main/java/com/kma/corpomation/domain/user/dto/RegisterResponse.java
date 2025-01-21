package com.kma.corpomation.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterResponse {
    private String accessToken;
    private String refreshToken;
}
