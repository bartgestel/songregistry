package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.model.Album;

import java.util.List;

public interface IAlbumService {
    List<Album> getAlbumsByArtistName(String artistName);
    List<Album> getAlbumsByAlbumTitle(String albumTitle);
}
