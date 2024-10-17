package com.bartvangestel.songregistrybackend.service.interfaces;

import com.bartvangestel.songregistrybackend.model.Album;

import java.util.List;

public interface IAlbumService {
    List<Album> getAlbumsByArtistName(String artistName);
    List<Album> getAlbumsByAlbumTitle(String albumTitle);
}
