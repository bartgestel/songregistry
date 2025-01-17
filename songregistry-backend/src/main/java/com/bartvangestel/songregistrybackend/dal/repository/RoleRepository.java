package com.bartvangestel.songregistrybackend.dal.repository;

import com.bartvangestel.songregistrybackend.dal.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findByName(String name);
}
