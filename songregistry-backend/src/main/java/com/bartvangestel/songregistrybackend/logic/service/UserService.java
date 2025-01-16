package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.logic.DTO.UserDTO;
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
public class UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final Set<String> BANNED_PASSWORDS = new HashSet<>();

    static {
        try (InputStream inputStream = UserService.class.getClassLoader().getResourceAsStream("bannedPasswords.txt")) {
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

    public boolean checkIfPasswordIsStrongEnough(String password) {
        boolean passwordLengthCheck = password.length() >= 14;
        boolean passwordContainsSpecialCharacterOrNumber =
                password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?].*") && password.matches(".*\\d.*");
        boolean isBannedPassword = BANNED_PASSWORDS.contains(password);

        return passwordLengthCheck && passwordContainsSpecialCharacterOrNumber && !isBannedPassword;
    }
}