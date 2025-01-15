package com.bartvangestel.songregistrybackend.dal;

import com.bartvangestel.songregistrybackend.dal.model.User;
import com.bartvangestel.songregistrybackend.dal.repository.UserRepository;
import com.bartvangestel.songregistrybackend.logic.DTO.UserDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IUserDAL;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAL implements IUserDAL {
    private final UserRepository userRepository;

    public UserDAL(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setHash(userDTO.getHash());
        userRepository.save(user);
    }

    public UserDTO getUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getEmail());
    }
}
