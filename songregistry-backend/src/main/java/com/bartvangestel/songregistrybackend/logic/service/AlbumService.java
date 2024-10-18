package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumDAL;
import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService implements IAlbumService {
    private final IAlbumDAL albumDAL;

    @Autowired
    public AlbumService(IAlbumDAL albumDAL) {
        this.albumDAL = albumDAL;
    }

    public List<Album> getAlbums() {
        return albumDAL.getAlbums();
    }

    @Override
    public List<Album> getAlbumsByArtistName(String name) {
        return albumDAL.getAlbumsByArtistName(name);
    }
    @Override
    public List<Album> getAlbumsByAlbumTitle(String title) {
        return albumDAL.getAlbumsByAlbumTitle(title);
    }
}
