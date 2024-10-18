package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.dal.repository.AlbumRepository;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService implements IAlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> getAlbumsByArtistName(String name) {
        return albumRepository.findByAlbumArtists_Artist_ArtistNameContainingIgnoreCase(name);
    }
    @Override
    public List<Album> getAlbumsByAlbumTitle(String title) {
        return albumRepository.findByAlbumNameContainingIgnoreCase(title);
    }
}
