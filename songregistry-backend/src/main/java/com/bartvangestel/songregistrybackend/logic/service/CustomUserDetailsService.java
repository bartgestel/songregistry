package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.logic.DTO.RegisterDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.UserDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IUserDAL;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserDAL userDAL;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(IUserDAL userDAL, @Lazy PasswordEncoder passwordEncoder) {
        this.userDAL = userDAL;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO userDTO = userDAL.getUserByEmail(email);
        if (userDTO == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if(userDTO.getRole() == null){
            userDTO.setRole("USER");
        }

        return User.builder()
                .username(userDTO.getEmail())
                .password(userDTO.getPassword())
                .roles(userDTO.getRole())
                .build();
    }

    public boolean userExists(String email, String username){
        return userDAL.existsByEmail(email) || userDAL.existsByUsername(username);
    }

    public void registerUser(RegisterDTO registerDTO){
        registerDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userDAL.registerUser(registerDTO);
    }
}
