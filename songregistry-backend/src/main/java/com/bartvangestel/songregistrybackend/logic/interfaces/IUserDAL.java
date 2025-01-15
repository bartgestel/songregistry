package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.logic.DTO.UserDTO;

public interface IUserDAL {
    void registerUser(UserDTO userDTO);
    UserDTO getUserByEmail(String email);
}
