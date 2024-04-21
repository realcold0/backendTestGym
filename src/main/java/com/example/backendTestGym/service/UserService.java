package com.example.backendTestGym.service;

import com.example.backendTestGym.domain.User;
import com.example.backendTestGym.dto.RegisterUserDTO;
import com.example.backendTestGym.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByEmail(registerUserDTO.getEMail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        // Password 암호화
        String encodedPassword = passwordEncoder.encode(registerUserDTO.getPassword());
        List<String> roles = new ArrayList<>();
        roles.add("USER");  // USER 권한 부여
        User user = new User(registerUserDTO.getEMail(), encodedPassword, roles);
        userRepository.save(user);
    }
}
