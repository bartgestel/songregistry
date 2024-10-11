package com.bartvangestel.songregistrybackend.service;

import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.repository.AlbumDAO;
import com.bartvangestel.songregistrybackend.repository.interfaces.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    private final AlbumDAO albumDAO;

    public AlbumService(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    public List<Album> getAlbumsByArtistName(String name) {
        return albumDAO.findAlbumByArtistName(name);
    }

    public List<Album> getAlbumsByAlbumTitle(String title) {
        return albumDAO.findAlbumByName(title);
    }
}
