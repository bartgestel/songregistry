package com.bartvangestel.songregistrybackend.logic.DTO;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String password;

    public UserDTO() {
    }

    public UserDTO(int id, String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
