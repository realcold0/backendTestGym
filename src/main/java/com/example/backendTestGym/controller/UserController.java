package com.example.backendTestGym.controller;

import com.example.backendTestGym.dto.RegisterUserDTO;
import com.example.backendTestGym.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserDTO registerUserDTO) {
        userService.signUp(registerUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body(registerUserDTO.getEMail());
    }
}
