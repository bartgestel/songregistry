package com.bartvangestel.songregistrybackend.repository;

import com.bartvangestel.songregistrybackend.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByAlbumNameContainingIgnoreCase(String albumTitle);
    List<Album> findByAlbumArtists_Artist_ArtistNameContainingIgnoreCase(String artistName);
}
