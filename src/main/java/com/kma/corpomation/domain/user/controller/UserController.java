package com.kma.corpomation.domain.user.controller;

import com.kma.corpomation.domain.user.dto.LoginRequest;
import com.kma.corpomation.domain.user.dto.LoginResponse;
import com.kma.corpomation.domain.user.dto.RegisterResponse;
import com.kma.corpomation.domain.user.dto.RegisterRequest;
import com.kma.corpomation.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(userService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok(userService.login(loginRequest));
    }
}