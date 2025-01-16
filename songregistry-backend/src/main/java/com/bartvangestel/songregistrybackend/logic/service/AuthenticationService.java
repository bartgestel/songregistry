package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.logic.DTO.AuthDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.RegisterDTO;
import com.bartvangestel.songregistrybackend.logic.JwtUtil;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;

    @Autowired
    public AuthenticationService(JwtUtil jwtUtil, PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
    }

    public String register(RegisterDTO registerDTO) throws BadRequestException {
        // Check if the username or email already exists (this will need to be handled by userDAL or a separate check)
        if (customUserDetailsService.userExists(registerDTO.getEmail(), registerDTO.getUsername())) {
            throw new BadRequestException("Username or Email already exists");
        }

        if (!userService.checkIfPasswordIsStrongEnough(registerDTO.getPassword())) {
            throw new BadRequestException("Password is not strong enough");
        }

        // Encrypt the password and register the user
        customUserDetailsService.registerUser(registerDTO);

        // Generate JWT token and return it
        return jwtUtil.generateToken(registerDTO.getEmail());
    }

    public String authenticate(AuthDTO authDTO) throws BadRequestException {
        // Fetch user details using CustomUserDetailsService
        UserDetails userDetails;
        try {
            userDetails = customUserDetailsService.loadUserByUsername(authDTO.getEmail());
        } catch (UsernameNotFoundException e) {
            throw new BadRequestException("User not found");
        }

        // Check password
        if (!passwordEncoder.matches(authDTO.getPassword().trim(), userDetails.getPassword().trim())) {
            System.out.println(userDetails.getPassword());
            throw new BadRequestException("Invalid password");
        }

        // Return JWT token after successful authentication
        return jwtUtil.generateToken(authDTO.getEmail());
    }
}
