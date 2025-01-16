package com.bartvangestel.songregistrybackend.dal;

import com.bartvangestel.songregistrybackend.auth.AuthenticationResponse;
import com.bartvangestel.songregistrybackend.config.JwtService;
import com.bartvangestel.songregistrybackend.dal.model.User;
import com.bartvangestel.songregistrybackend.dal.model.UserRole;
import com.bartvangestel.songregistrybackend.dal.repository.UserRepository;
import com.bartvangestel.songregistrybackend.logic.DTO.AuthDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.RegisterDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.UserDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IUserDAL;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAL implements IUserDAL {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserDAL(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse registerUser(RegisterDTO registerDTO){
        UserRole userRole = new UserRole();
        userRole.setName("USER");

        User user = User.builder()
                .username(registerDTO.getUsername())
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .userRole(userRole)
                .build();

        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticateUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
