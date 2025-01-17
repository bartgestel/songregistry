package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.logic.DTO.RegisterDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.UserDTO;

public interface IUserDAL {
    void registerUser(RegisterDTO registerDTO);
    UserDTO getUserByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
