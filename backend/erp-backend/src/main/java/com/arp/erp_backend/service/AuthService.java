package com.arp.erp_backend.service;


import com.arp.erp_backend.dto.LoginRequest;
import com.arp.erp_backend.dto.RegisterRequest;
import com.arp.erp_backend.entity.Role;
import com.arp.erp_backend.entity.User;
import com.arp.erp_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder  passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {
        // create new user object
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // encrypt password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.USER);
        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(user.getEmail());
    }

}
