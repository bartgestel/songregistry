package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.DTO.ArtistDTO;

import java.util.List;

public interface IArtistDAL {
    List<ArtistDTO> getArtists();
    List<ArtistDTO> getArtistsByName(String name);
    List<ArtistDTO> getArtistsForHome();
    ArtistDTO getArtistById(int id);

    List<ArtistDTO> getArtistsBySongId(Integer id);

    List<ArtistDTO> getArtistsByAlbumId(Integer id);
}
