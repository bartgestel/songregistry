package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.dal.model.Album;

import java.util.List;

public interface IAlbumDAL {
    List<AlbumDTO> getAlbums();
    List<AlbumDTO> getAlbumsByArtistName(String artistName);
    List<AlbumDTO> getAlbumsByAlbumTitle(String albumTitle);
    List<AlbumDTO> getAlbumsForHome();
    List<AlbumDTO> getAlbumsForArtist(int artistId);
    AlbumDTO getAlbumById(int id);
    void addAlbum(AlbumDTO albumDTO);
}
