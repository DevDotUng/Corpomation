package com.kma.corpomation.domain.user;

import com.kma.corpomation.ApiTest;
import com.kma.corpomation.domain.user.dto.*;
import com.kma.corpomation.domain.user.entity.User;
import com.kma.corpomation.domain.user.repository.UserRepository;
import com.kma.corpomation.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest extends ApiTest {

    private RegisterRequest registerRequest;
    private RegisterResponse registerResponse;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        registerRequest = new RegisterRequest("user", "email@naver.com", "1234", UserRole.USER);
        registerResponse = userService.register(registerRequest);
    }

    @Test
    void register() {
        User user = userRepository.findByEmail("email@naver.com").get();

        Assert.hasText(registerResponse.getAccessToken(), "엑세스 토큰이 없습니다.");
        Assert.hasText(registerResponse.getRefreshToken(), "리프레시 토큰이 없습니다.");

        assertThat(user.getUsername()).isEqualTo("user");
        assertThat(user.getEmail()).isEqualTo("email@naver.com");
        assertThat(user.getPassword()).isEqualTo("1234");
    }

    @Test
    void login() throws Exception {
        LoginRequest loginRequest = new LoginRequest("email@naver.com", "1234");
        LoginResponse loginResponse = userService.login(loginRequest);

        Assert.hasText(loginResponse.getAccessToken(), "엑세스 토큰이 없습니다.");
        Assert.hasText(loginResponse.getRefreshToken(), "리프레시 토큰이 없습니다.");
    }
}
