// src/main/java/com/bartvangestel/songregistrybackend/SongregistryBackendApplication.java
package com.bartvangestel.songregistrybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SongregistryBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(SongregistryBackendApplication.class, args);
    }
}