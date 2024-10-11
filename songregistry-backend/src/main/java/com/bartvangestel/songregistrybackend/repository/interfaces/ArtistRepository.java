package com.bartvangestel.songregistrybackend.repository.interfaces;

import com.bartvangestel.songregistrybackend.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    List<Artist> findByArtistNameContainingIgnoreCase(String name);
}
