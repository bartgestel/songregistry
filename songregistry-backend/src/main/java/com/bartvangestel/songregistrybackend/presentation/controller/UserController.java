package com.bartvangestel.songregistrybackend.presentation.controller;

import com.bartvangestel.songregistrybackend.logic.DTO.AuthDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.RegisterDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.UserDTO;
import com.bartvangestel.songregistrybackend.logic.service.AuthenticationService;
import com.bartvangestel.songregistrybackend.logic.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AuthenticationService authenticationService;

    public UserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Register user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) {
        try {
            String token = authenticationService.register(registerDTO);
            return ResponseEntity.status(201).body(token);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthDTO authDTO) {
        try {
            String token = authenticationService.authenticate(authDTO);
            return ResponseEntity.status(200).body(token);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
