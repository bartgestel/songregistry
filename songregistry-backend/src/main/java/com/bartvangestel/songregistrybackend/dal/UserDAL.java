package com.bartvangestel.songregistrybackend.dal;

import com.bartvangestel.songregistrybackend.dal.model.User;
import com.bartvangestel.songregistrybackend.dal.repository.RoleRepository;
import com.bartvangestel.songregistrybackend.dal.repository.UserRepository;
import com.bartvangestel.songregistrybackend.logic.DTO.RegisterDTO;
import com.bartvangestel.songregistrybackend.logic.DTO.UserDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IUserDAL;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAL implements IUserDAL {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDAL(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void registerUser(RegisterDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setHash(userDTO.getPassword());
        user.setRole(roleRepository.findByName("USER"));
        userRepository.save(user);
    }

    public UserDTO getUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getEmail());
    }
}
