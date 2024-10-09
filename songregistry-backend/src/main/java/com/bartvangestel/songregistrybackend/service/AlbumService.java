package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> getAlbumsByArtistName(String name) {
        return albumRepository.findByAlbumArtists_Artist_ArtistNameContainingIgnoreCase(name);
    }

    public List<Album> getAlbumsByAlbumTitle(String title) {
        return albumRepository.findByAlbumNameContainingIgnoreCase(title);
    }
}
