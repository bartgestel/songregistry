package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.auth.AuthenticationResponse;
import com.bartvangestel.songregistrybackend.logic.DTO.RegisterDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.AuthDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.UserDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IUserDAL;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {


    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final Set<String> BANNED_PASSWORDS = new HashSet<>();
    private final IUserDAL userDAL;
    private final AuthenticationManager authenticationManager;

    public UserService(IUserDAL userDAL, AuthenticationManager authenticationManager) {
        this.userDAL = userDAL;
        this.authenticationManager = authenticationManager;
    }

    static {
        try (InputStream inputStream = UserService.class.getClassLoader().getResourceAsStream("banned-passwords.txt")) {
            if (inputStream == null) {
                throw new RuntimeException("banned-passwords.txt not found in the resources folder");
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    BANNED_PASSWORDS.add(line.trim());
                }
            }
        } catch (IOException e) {
            logger.error("Failed to load banned passwords", e);
            throw new RuntimeException("Failed to load banned passwords", e);
        }
    }

    public AuthenticationResponse registerUser(RegisterDTO registerDTO) {
        if (!checkIfPasswordIsStrongEnough(registerDTO.getPassword())) {
            throw new IllegalArgumentException("Password is not strong enough");
        }
        return userDAL.registerUser(registerDTO);
    }

    public AuthenticationResponse loginUser(AuthDTO authDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword())
        );
        return userDAL.authenticateUser(authDTO.getEmail());

    }

    private boolean checkIfPasswordIsStrongEnough(String password) {
        boolean passwordLengthCheck = password.length() >= 14;
        boolean passwordContainsSpecialCharacterOrNumber =
                password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?].*") && password.matches(".*\\d.*");
        boolean isBannedPassword = BANNED_PASSWORDS.contains(password);

        return passwordLengthCheck && passwordContainsSpecialCharacterOrNumber && !isBannedPassword;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userDAL.getUserByEmail(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(userDTO.getEmail())
                .password(userDTO.getPassword())
                .roles("USER") // You can set roles as per your requirement
                .build();
    }
}