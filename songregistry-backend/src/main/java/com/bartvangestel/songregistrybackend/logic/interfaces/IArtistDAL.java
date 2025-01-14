package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.logic.DTO.ArtistDTO;

import java.util.List;

public interface IArtistDAL {
    List<ArtistDTO> getArtists();
    List<ArtistDTO> getArtistsByName(String name);
    List<ArtistDTO> getArtistsForHome();
    ArtistDTO getArtistById(int id);
    void addArtist(ArtistDTO artist);
}
