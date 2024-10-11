package com.bartvangestel.songregistrybackend.repository;

import com.bartvangestel.songregistrybackend.model.Artist;
import com.bartvangestel.songregistrybackend.repository.interfaces.ArtistRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtistDAO {
    private final ArtistRepository artistRepository;

    public ArtistDAO(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    public List<Artist> findArtistByName(String artistName) {
        return artistRepository.findByArtistNameContainingIgnoreCase(artistName);
    }
}
