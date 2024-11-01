package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.dal.model.Album;

import java.util.List;

public interface IAlbumService {
    List<AlbumDTO> getAlbumsByArtistName(String artistName);
    List<AlbumDTO> getAlbumsByAlbumTitle(String albumTitle);
    List<AlbumDTO> getAlbumsForHome();
}
