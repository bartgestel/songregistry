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
        List<Album> albums = albumDAL.getAlbums();
        return albums.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AlbumDTO> getAlbumsByArtistName(String name) {
        List<Album> albums = albumDAL.getAlbumsByArtistName(name);
        return albums.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AlbumDTO> getAlbumsByAlbumTitle(String title) {
        List<Album> albums = albumDAL.getAlbumsByAlbumTitle(title);
        return albums.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AlbumDTO> getAlbumsForHome() {
        List<Album> albums = albumDAL.getAlbumsForHome();
        return albums.stream().map(this::convertToDTOWithRelations).collect(Collectors.toList());
    }

    private AlbumDTO convertToDTO(Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setAlbumName(album.getAlbumName());
        return albumDTO;
    }

    private AlbumDTO convertToDTOWithRelations(Album album) {
        AlbumDTO albumDTO = convertToDTO(album);
        albumDTO.setAlbumArtists(album.getAlbumArtists().stream()
                .map(albumArtist -> convertToDTO(albumArtist.getArtist()))
                .collect(Collectors.toList()));
        return albumDTO;
    }

    private ArtistDTO convertToDTO(Artist artist) {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setId(artist.getId());
        artistDTO.setArtistName(artist.getArtistName());
        return artistDTO;
    }
}