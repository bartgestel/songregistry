// src/main/java/com/bartvangestel/songregistrybackend/SongregistryBackendApplication.java
package com.bartvangestel.songregistrybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bartvangestel.songregistrybackend.dal")
@EntityScan(basePackages = "com.bartvangestel.songregistrybackend.dal.model")
public class SongregistryBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(SongregistryBackendApplication.class, args);
    }
}