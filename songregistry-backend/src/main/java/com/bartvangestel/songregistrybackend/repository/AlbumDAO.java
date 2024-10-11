package com.bartvangestel.songregistrybackend.repository;

import com.bartvangestel.songregistrybackend.model.Album;
import com.bartvangestel.songregistrybackend.repository.interfaces.AlbumRepository;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlbumDAO {
    private final AlbumRepository albumRepository;

    public AlbumDAO(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> findAlbumByName(String albumName) {
        return albumRepository.findByAlbumNameContainingIgnoreCase(albumName);
    }

    public List<Album> findAlbumByArtistName(String artistName) {
        return albumRepository.findByAlbumArtists_Artist_ArtistNameContainingIgnoreCase(artistName);
    }
}
