package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.DTO.ArtistDTO;
import com.bartvangestel.songregistrybackend.DTO.SongDTO;
import com.bartvangestel.songregistrybackend.dal.model.Album;
import com.bartvangestel.songregistrybackend.dal.model.Song;

import java.util.List;

public interface IArtistService {
    List<ArtistDTO> getArtists();
    List<ArtistDTO> getArtistByName(String name);
    List<AlbumDTO> getAlbumsByArtistName(String name);
    List<SongDTO> getSongsByArtistName(String name);
    List<ArtistDTO> getArtistsForHome();
    ArtistDTO getArtistById(int id);
}
