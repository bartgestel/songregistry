package com.bartvangestel.songregistrybackend.logic.service;

import com.bartvangestel.songregistrybackend.DTO.AlbumDTO;
import com.bartvangestel.songregistrybackend.DTO.ArtistDTO;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumDAL;
import com.bartvangestel.songregistrybackend.dal.model.Album;
import com.bartvangestel.songregistrybackend.logic.interfaces.IAlbumService;
import com.bartvangestel.songregistrybackend.dal.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService implements IAlbumService {
    private final IAlbumDAL albumDAL;

    @Autowired
    public AlbumService(IAlbumDAL albumDAL) {
        this.albumDAL = albumDAL;
    }

    public List<AlbumDTO> getAlbums() {
        return albumDAL.getAlbums();
    }

    @Override
    public List<AlbumDTO> getAlbumsByArtistName(String name) {
        return albumDAL.getAlbumsByArtistName(name);
    }

    @Override
    public List<AlbumDTO> getAlbumsByAlbumTitle(String title) {
        return albumDAL.getAlbumsByAlbumTitle(title);
    }

    @Override
    public List<AlbumDTO> getAlbumsForHome() {
        return albumDAL.getAlbumsForHome();
    }
}