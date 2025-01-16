package com.bartvangestel.songregistrybackend.dal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "user_roles")
@AllArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "userRole")
    private Set<User> users = new LinkedHashSet<>();

    public UserRole() {
    }

    public UserRole(String name) {
        this.name = name;
    }

}
