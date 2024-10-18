package com.bartvangestel.songregistrybackend.dal;

import com.bartvangestel.songregistrybackend.dal.repository.AlbumRepository;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumDAL;
import com.bartvangestel.songregistrybackend.model.Album;

import java.util.List;

public class AlbumDAL implements IAlbumDAL {
    private final AlbumRepository albumRepository;

    public AlbumDAL(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> getAlbumsByArtistName(String artistName) {
        return albumRepository.findByAlbumArtists_Artist_ArtistNameContainingIgnoreCase(artistName);
    }

    public List<Album> getAlbumsByAlbumTitle(String albumTitle) {
        return albumRepository.findByAlbumNameContainingIgnoreCase(albumTitle);
    }
}
