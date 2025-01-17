package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.logic.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void checkIfPasswordIsStrongEnough_ValidPassword_ReturnsTrue() {
        String validPassword = "StrongPassw0rd!";

        assertTrue(userService.checkIfPasswordIsStrongEnough(validPassword));
    }

    @Test
    void checkIfPasswordIsStrongEnough_ShortPassword_ReturnsFalse() {
        String shortPassword = "Short1!";

        assertFalse(userService.checkIfPasswordIsStrongEnough(shortPassword));
    }

    @Test
    void checkIfPasswordIsStrongEnough_NoSpecialCharacterOrNumber_ReturnsFalse() {
        String noSpecialCharOrNumber = "NoSpecialCharOrNumber";

        assertFalse(userService.checkIfPasswordIsStrongEnough(noSpecialCharOrNumber));
    }

    @Test
    void checkIfPasswordIsStrongEnough_BannedPassword_ReturnsFalse() {
        String bannedPassword = "1234567890";

        assertFalse(userService.checkIfPasswordIsStrongEnough(bannedPassword));
    }

    @Test
    void checkIfPasswordIsStrongEnough_EmptyPassword_ReturnsFalse() {
        String emptyPassword = "";

        assertFalse(userService.checkIfPasswordIsStrongEnough(emptyPassword));
    }
}