package com.bartvangestel.songregistrybackend.repository;

import com.bartvangestel.songregistrybackend.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByAlbumNameContainingIgnoreCase(String albumTitle);
    List<Album> findByAlbumArtists_Artist_ArtistNameContainingIgnoreCase(String artistName);
}
