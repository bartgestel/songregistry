package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.auth.AuthenticationResponse;
import com.bartvangestel.songregistrybackend.logic.DTO.RegisterDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.UserDTO;

public interface IUserDAL {
    AuthenticationResponse registerUser(RegisterDTO registerDTO);

    AuthenticationResponse authenticateUser(String email);
    UserDTO getUserByEmail(String email);
}


