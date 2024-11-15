package com.bartvangestel.songregistrybackend.logic.interfaces;

import com.bartvangestel.songregistrybackend.dal.model.Album;

import java.util.List;

public interface IAlbumDAL {
    public List<Album> getAlbums();
    public List<Album> getAlbumsByArtistName(String artistName);
    public List<Album> getAlbumsByAlbumTitle(String albumTitle);
    public List<Album> getAlbumsForHome();
}
