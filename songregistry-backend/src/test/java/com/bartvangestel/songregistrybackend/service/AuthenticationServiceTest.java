package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.logic.DTO.AuthDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.RegisterDTO;
import com.bartvangestel.songregistrybackend.logic.JwtUtil;
import com.bartvangestel.songregistrybackend.logic.service.AuthenticationService;
import com.bartvangestel.songregistrybackend.logic.service.CustomUserDetailsService;
import com.bartvangestel.songregistrybackend.logic.service.UserService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_successfulRegistration_returnsJwtToken() throws BadRequestException {
        RegisterDTO registerDTO = new RegisterDTO("username", "email@example.com", "StrongPassword123");
        when(customUserDetailsService.userExists(registerDTO.getEmail(), registerDTO.getUsername())).thenReturn(false);
        when(userService.checkIfPasswordIsStrongEnough(registerDTO.getPassword())).thenReturn(true);
        when(jwtUtil.generateToken(registerDTO.getEmail())).thenReturn("jwtToken");

        String result = authenticationService.register(registerDTO);

        assertEquals("jwtToken", result);
        verify(customUserDetailsService).registerUser(registerDTO);
    }

    @Test
    void register_userAlreadyExists_throwsBadRequestException() {
        RegisterDTO registerDTO = new RegisterDTO("username", "email@example.com", "StrongPassword123");
        when(customUserDetailsService.userExists(registerDTO.getEmail(), registerDTO.getUsername())).thenReturn(true);

        assertThrows(BadRequestException.class, () -> authenticationService.register(registerDTO));
    }

    @Test
    void register_weakPassword_throwsBadRequestException() {
        RegisterDTO registerDTO = new RegisterDTO("username", "email@example.com", "weak");
        when(customUserDetailsService.userExists(registerDTO.getEmail(), registerDTO.getUsername())).thenReturn(false);
        when(userService.checkIfPasswordIsStrongEnough(registerDTO.getPassword())).thenReturn(false);

        assertThrows(BadRequestException.class, () -> authenticationService.register(registerDTO));
    }

    @Test
    void authenticate_successfulAuthentication_returnsJwtToken() throws BadRequestException {
        AuthDTO authDTO = new AuthDTO("email@example.com", "password");
        UserDetails userDetails = mock(UserDetails.class);
        when(customUserDetailsService.loadUserByUsername(authDTO.getEmail())).thenReturn(userDetails);
        when(userDetails.getPassword()).thenReturn("encodedPassword");
        when(passwordEncoder.matches(authDTO.getPassword().trim(), userDetails.getPassword().trim())).thenReturn(true);
        when(jwtUtil.generateToken(authDTO.getEmail())).thenReturn("jwtToken");

        String result = authenticationService.authenticate(authDTO);

        assertEquals("jwtToken", result);
    }

    @Test
    void authenticate_userNotFound_throwsBadRequestException() {
        AuthDTO authDTO = new AuthDTO("email@example.com", "password");
        when(customUserDetailsService.loadUserByUsername(authDTO.getEmail())).thenThrow(new UsernameNotFoundException("User not found"));

        assertThrows(BadRequestException.class, () -> authenticationService.authenticate(authDTO));
    }

    @Test
    void authenticate_invalidPassword_throwsBadRequestException() {
        AuthDTO authDTO = new AuthDTO("email@example.com", "wrongPassword");
        UserDetails userDetails = mock(UserDetails.class);
        when(customUserDetailsService.loadUserByUsername(authDTO.getEmail())).thenReturn(userDetails);
        when(userDetails.getPassword()).thenReturn("encodedPassword");
        when(passwordEncoder.matches(authDTO.getPassword().trim(), userDetails.getPassword().trim())).thenReturn(false);

        assertThrows(BadRequestException.class, () -> authenticationService.authenticate(authDTO));
    }
}