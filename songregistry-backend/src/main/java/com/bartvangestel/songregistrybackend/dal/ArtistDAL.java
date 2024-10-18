package com.bartvangestel.songregistrybackend.dal;

import com.bartvangestel.songregistrybackend.dal.repository.AlbumRepository;
import com.bartvangestel.songregistrybackend.dal.repository.ArtistRepository;
import com.bartvangestel.songregistrybackend.logic.interfaces.IArtistDAL;
import com.bartvangestel.songregistrybackend.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArtistDAL implements IArtistDAL {
    private final ArtistRepository artistRepository;

    public ArtistDAL(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }

    @Override
    public List<Artist> getArtistsByName(String name) {
        return artistRepository.findByArtistNameContainingIgnoreCase(name);
    }

}
