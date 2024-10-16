package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.repository.AlbumRepository;
import com.bartvangestel.songregistrybackend.service.interfaces.IAlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements IAlbumService {
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
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