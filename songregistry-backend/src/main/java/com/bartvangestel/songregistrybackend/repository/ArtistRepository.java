package com.bartvangestel.songregistrybackend.repository;

import com.bartvangestel.songregistrybackend.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    List<Artist> findByArtistNameContainingIgnoreCase(String name);
}
