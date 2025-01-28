package com.kma.corpomation.domain.user.service;

import com.kma.corpomation.domain.user.dto.LoginRequest;
import com.kma.corpomation.domain.user.dto.LoginResponse;
import com.kma.corpomation.domain.user.dto.RegisterRequest;
import com.kma.corpomation.domain.user.dto.RegisterResponse;
import com.kma.corpomation.domain.user.entity.Token;
import com.kma.corpomation.domain.user.entity.User;
import com.kma.corpomation.domain.user.jwt.JwtGenerator;
import com.kma.corpomation.domain.user.jwt.JwtUtil;
import com.kma.corpomation.domain.user.repository.TokenRepository;
import com.kma.corpomation.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtGenerator jwtGenerator;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.access-secret}")
    private String ACCESS_SECRET_KEY;
    @Value("${jwt.refresh-secret}")
    private String REFRESH_SECRET_KEY;
    @Value("${jwt.access-expiration}")
    private long ACCESS_EXPIRATION;
    @Value("${jwt.refresh-expiration}")
    private long REFRESH_EXPIRATION;


    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = new User(registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getUserRole());

        User savedUser = userRepository.save(user);

        String accessToken = jwtGenerator.generateAccessToken(jwtUtil.getSigningKey(ACCESS_SECRET_KEY), ACCESS_EXPIRATION, user);
        String refreshToken = jwtGenerator.generateRefreshToken(jwtUtil.getSigningKey(REFRESH_SECRET_KEY), REFRESH_EXPIRATION, user);

        saveToken(accessToken, refreshToken);

        return new RegisterResponse(accessToken, refreshToken);
    }

    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        var user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new Exception("이메일이 정확하지 않습니다."));

        String accessToken = jwtGenerator.generateAccessToken(jwtUtil.getSigningKey(ACCESS_SECRET_KEY), ACCESS_EXPIRATION, user);
        String refreshToken = jwtGenerator.generateRefreshToken(jwtUtil.getSigningKey(REFRESH_SECRET_KEY), REFRESH_EXPIRATION, user);

        saveToken(accessToken, refreshToken);

        return new LoginResponse(accessToken, refreshToken);
    }

    private void saveToken(String accessToken, String refreshToken) {
        Token token = new Token(accessToken, refreshToken);

        tokenRepository.save(token);
    }
}
