package com.bartvangestel.songregistrybackend.presentation.controller;

import com.bartvangestel.songregistrybackend.DTO.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping("/register")
    public void register(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getUsername());
    }
}
