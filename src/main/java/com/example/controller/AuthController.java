package com.example.controller;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserDetailsService userDetailsService;
    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return jwtUtil.generateToken(userDetails.getUsername(), user.getRole().getName());
    }

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public String register(@RequestBody User newUser) {
        if (userRepository.existsByEmail(newUser.getEmail())) {
            return "User already exists with this email!";
        }

        Role defaultRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole(defaultRole);
        userRepository.save(newUser);

        return "User registered successfully!";
    }
}
